package nl.doyle.mccloud.tourdefrance.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

import nl.doyle.mccloud.tourdefrance.dao.DeelnemerDao;
import nl.doyle.mccloud.tourdefrance.dao.EindUitslagDao;
import nl.doyle.mccloud.tourdefrance.dao.PloegenTijdritDao;
import nl.doyle.mccloud.tourdefrance.dao.StandaardEtappeDao;
import nl.doyle.mccloud.tourdefrance.dto.DeelnemerWithRennersDto;
import nl.doyle.mccloud.tourdefrance.dto.RennerDto;
import nl.doyle.mccloud.tourdefrance.valueobjects.AbstractEtappeAndEindUitslag;
import nl.doyle.mccloud.tourdefrance.valueobjects.Deelnemer;
import nl.doyle.mccloud.tourdefrance.valueobjects.EindUitslag;
import nl.doyle.mccloud.tourdefrance.valueobjects.Etappe;
import nl.doyle.mccloud.tourdefrance.valueobjects.PloegenTijdrit;
import nl.doyle.mccloud.tourdefrance.valueobjects.Renner;
import nl.doyle.mccloud.tourdefrance.valueobjects.StandaardEtappe;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class TourFacadeImpl implements TourFacade {
	
	private static final Log logger = LogFactory.getLog(TourFacadeImpl.class);
	private DeelnemerDao deelnemerDao;
	private StandaardEtappeDao standaardEtappeDao;
	private PloegenTijdritDao ploegenTijdritDao;
	private EindUitslagDao eindUitslagDao;
	
	
	public TourFacadeImpl() {
	}
	
	
	/**
	 * Geeft de deelnemers met hun renners terug
	 * 
	 * @return De deelnemers met hun renners
	 */
	public List<DeelnemerWithRennersDto> getAllDeelnemersAndRenners() {
		//Laadt alle deelnemers Eager uit de database
		List<Deelnemer> deelnemers = deelnemerDao.loadAllDeelnemersEager();
		
		List<DeelnemerWithRennersDto> allDeelnemersAndRenners = new ArrayList<DeelnemerWithRennersDto>();
		for(Deelnemer nextDeelnemer: deelnemers) {
			DeelnemerWithRennersDto deelnemer = new DeelnemerWithRennersDto();
			deelnemer.setNummer(nextDeelnemer.getNummer());
			deelnemer.setAchternaam(nextDeelnemer.getAchternaam());
			deelnemer.setVoornaam(nextDeelnemer.getVoornaam());
			deelnemer.setEmail(nextDeelnemer.getEmail());
			deelnemer.setRekeningnummer(nextDeelnemer.getRekeningnummer());
			deelnemer.setRenners(new TreeSet<RennerDto>(new RennerComparator()));
			//Nu renners in DTO kopieren
			for (Renner nextRenner: nextDeelnemer.getRenners()) {
				RennerDto renner = new RennerDto();
				renner.setNummer(nextRenner.getNummer());
				renner.setAchternaam(nextRenner.getAchternaam());
				renner.setVoornaam(nextRenner.getVoornaam());
				deelnemer.getRenners().add(renner);
			}
			
			//deelnemerAndRenners = (List<DeelnemerWithRennersDto>) mapper.map(nextDeelnemer, java.util.List.class);
			allDeelnemersAndRenners.add(deelnemer);
		}
		return allDeelnemersAndRenners;
	}
	
	private class RennerComparator implements Comparator {

		public int compare(Object o1, Object o2) {
			int comparison = 0;
			
			if (o1 instanceof RennerDto && o2 instanceof RennerDto) {
				//Als 1 van de twee een kopman is dan is dat altijd de kleinste
				if (((RennerDto) o1).getNummer() % 10 == 1) {
					comparison = -1;
				} else if (((RennerDto) o2).getNummer() % 10 == 1 ) {
					comparison = 1;
				} else if (((RennerDto) o1).getNummer() >  ((RennerDto)o2).getNummer()) {
					comparison = 1;
				} else if (((RennerDto) o1).getNummer() <  ((RennerDto)o2).getNummer()) {
					comparison = -1;
				}
			} else {
				throw new ClassCastException("Objects not of the correct type and can't be compared.");
			}
			
			
			return comparison;
		}
		
		
		
	}
	
	
	/**
	 * Geeft de opgevraagde etappe terug inclusief uitslag(en) en start- en finishplaats.
	 * 
	 * @param etappeNummer Het nummer van de etappe
	 * @return Etappe
	 */
	public AbstractEtappeAndEindUitslag getEtappeWithUitslag(int etappeNummer) {
		AbstractEtappeAndEindUitslag etappe;
		if (etappeNummer == 0) {
			etappe = eindUitslagDao.loadEindUitslagWithUitslagEager();
		} else 
			//Haal de etappe op uit de db. Als het geen standaardetappe is, haal dan een ploegentijdrit op.
			etappe = standaardEtappeDao.loadStandaardEtappeWithUitslagEager(etappeNummer);
			if (etappe == null) {
				etappe = ploegenTijdritDao.loadPloegenTijdritWithUitslagEager(etappeNummer);
			} 
		return etappe;
	}
	/**
	 * Geeft de opgevraagde etappe terug inclusief start- en finishplaats
	 * 
	 * 
	 * @param etappeNummer Het nummer van de etappe
	 * @return Etappe
	 */
	public Etappe getEtappeWithStartAndFinish(int etappeNummer) {
		Etappe etappe  = standaardEtappeDao.loadStandaardEtappeWithStartAndFinish(etappeNummer);
		if (etappe == null) {
			etappe = ploegenTijdritDao.loadPloegenTijdritWithStartAndFinish(etappeNummer);
		}	
		return etappe;
	}
	
	public void saveEtappe(AbstractEtappeAndEindUitslag etappe) {
		if (etappe instanceof StandaardEtappe) {
			standaardEtappeDao.saveStandaardEtappe((StandaardEtappe) etappe);
		} else if (etappe instanceof PloegenTijdrit) {
			ploegenTijdritDao.savePloegenTijdrit((PloegenTijdrit) etappe);
		} else  if (etappe instanceof EindUitslag){
			eindUitslagDao.saveEindUitslag((EindUitslag) etappe);
		} else { 
			throw new RuntimeException("Etappe kan niet worden opgeslagen. Etappe is van het verkeerde type.");
		}
	}
	
	
	/**
	 * @return the deelnemerDao
	 */
	public DeelnemerDao getDeelnemerDao() {
		return deelnemerDao;
	}


	/**
	 * @param deelnemerDao the deelnemerDao to set
	 */
	public void setDeelnemerDao(DeelnemerDao deelnemerDao) {
		this.deelnemerDao = deelnemerDao;
	}

	/**
	 * @return the ploegenTijdritDao
	 */
	public PloegenTijdritDao getPloegenTijdritDao() {
		return ploegenTijdritDao;
	}


	/**
	 * @param ploegenTijdritDao the ploegenTijdritDao to set
	 */
	public void setPloegenTijdritDao(PloegenTijdritDao ploegenTijdritDao) {
		this.ploegenTijdritDao = ploegenTijdritDao;
	}


	/**
	 * @return the standaardEtappeDao
	 */
	public StandaardEtappeDao getStandaardEtappeDao() {
		return standaardEtappeDao;
	}


	/**
	 * @param standaardEtappeDao the standaardEtappeDao to set
	 */
	public void setStandaardEtappeDao(StandaardEtappeDao standaardEtappeDao) {
		this.standaardEtappeDao = standaardEtappeDao;
	}


	/**
	 * @return the eindUitslagDao
	 */
	public EindUitslagDao getEindUitslagDao() {
		return eindUitslagDao;
	}


	/**
	 * @param eindUitslagDao the eindUitslagDao to set
	 */
	public void setEindUitslagDao(EindUitslagDao eindUitslagDao) {
		this.eindUitslagDao = eindUitslagDao;
	}
	
}
