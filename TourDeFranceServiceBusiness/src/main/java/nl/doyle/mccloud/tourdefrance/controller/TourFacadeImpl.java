package nl.doyle.mccloud.tourdefrance.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import net.sf.dozer.util.mapping.MapperIF;
import nl.doyle.mccloud.tourdefrance.dao.DeelnemerDao;
import nl.doyle.mccloud.tourdefrance.dao.EindUitslagDao;
import nl.doyle.mccloud.tourdefrance.dao.PloegenTijdritDao;
import nl.doyle.mccloud.tourdefrance.dao.StandaardEtappeDao;
import nl.doyle.mccloud.tourdefrance.dto.DeelnemerRennerDto;
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
	private MapperIF mapper;	
	public TourFacadeImpl() {
	}
	
	
	/**
	 * Geeft de deelnemers met hun renners terug
	 * 
	 * @return De deelnemers met hun renners
	 */
	public List<DeelnemerRennerDto> getAllDeelnemersAndRenners() {
		//Laadt alle deelnemers Eager uit de database
		List<Deelnemer> deelnemers = deelnemerDao.loadAllDeelnemersEager();
		
		List<DeelnemerRennerDto> deelnemerAndRenners = null;
		List<DeelnemerRennerDto> allDeelnemersAndRenners = new ArrayList<DeelnemerRennerDto>();
		for(Deelnemer nextDeelnemer: deelnemers) {
			deelnemerAndRenners = (List<DeelnemerRennerDto>) mapper.map(nextDeelnemer, java.util.List.class);
			allDeelnemersAndRenners.addAll(deelnemerAndRenners);
		}
		return allDeelnemersAndRenners;
	}
	
	/**
	 * Geeft de opgevraagde etappe terug inclusief uitslag(en) en start- en finishplaats.
	 * 
	 * @param etappeNummer Het nummer van de etappe
	 * @return Etappe
	 */
	public AbstractEtappeAndEindUitslag getEtappeWithUitslag(int etappeNummer) {
		//Haal de etappe op uit de db. Als het geen standaardetappe is, haal dan een ploegentijdrit op.
		AbstractEtappeAndEindUitslag etappe  = standaardEtappeDao.loadStandaardEtappeWithUitslagEager(etappeNummer);
		if (etappe == null) {
			etappe = ploegenTijdritDao.loadPloegenTijdritWithUitslagEager(etappeNummer);
		} 
		//Als het ook geen ploegentijdrit is, probeer dan de EindUitslag
		if (etappe == null) {
			etappe = eindUitslagDao.loadEindUitslagWithUitslagEager();
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
	 * @return the mapper
	 */
	public MapperIF getMapper() {
		return mapper;
	}


	/**
	 * @param mapper the mapper to set
	 */
	public void setMapper(MapperIF mapper) {
		this.mapper = mapper;
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
