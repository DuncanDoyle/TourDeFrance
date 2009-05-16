package nl.doyle.mccloud.tourdefrance.controller;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;

import nl.doyle.mccloud.tourdefrance.dao.DeelnemerDao;
import nl.doyle.mccloud.tourdefrance.dao.EindUitslagDao;
import nl.doyle.mccloud.tourdefrance.dao.PloegenTijdritDao;
import nl.doyle.mccloud.tourdefrance.dao.StandaardEtappeDao;
import nl.doyle.mccloud.tourdefrance.dao.UitslagBedragDao;
import nl.doyle.mccloud.tourdefrance.dto.AbstractEtappeAndEindUitslagDto;
import nl.doyle.mccloud.tourdefrance.dto.DeelnemerDto;
import nl.doyle.mccloud.tourdefrance.dto.DeelnemerWithRennersDto;
import nl.doyle.mccloud.tourdefrance.dto.EindUitslagDto;
import nl.doyle.mccloud.tourdefrance.dto.PloegenTijdritDto;
import nl.doyle.mccloud.tourdefrance.dto.RennerDto;
import nl.doyle.mccloud.tourdefrance.dto.StandaardEtappeDto;
import nl.doyle.mccloud.tourdefrance.dto.UitslagDto;
import nl.doyle.mccloud.tourdefrance.valueobjects.AbstractEtappeAndEindUitslag;
import nl.doyle.mccloud.tourdefrance.valueobjects.Deelnemer;
import nl.doyle.mccloud.tourdefrance.valueobjects.EindUitslag;
import nl.doyle.mccloud.tourdefrance.valueobjects.Etappe;
import nl.doyle.mccloud.tourdefrance.valueobjects.GeleTruiUitslag;
import nl.doyle.mccloud.tourdefrance.valueobjects.PloegenTijdrit;
import nl.doyle.mccloud.tourdefrance.valueobjects.Renner;
import nl.doyle.mccloud.tourdefrance.valueobjects.StandaardEtappe;
import nl.doyle.mccloud.tourdefrance.valueobjects.Uitslag;
import nl.doyle.mccloud.tourdefrance.valueobjects.UitslagBedrag;
import nl.doyle.mccloud.tourdefrance.valueobjects.UitslagBedrag.Categorien;

public class TourFacadeImpl implements TourFacade {

	private DeelnemerDao deelnemerDao;
	private StandaardEtappeDao standaardEtappeDao;
	private PloegenTijdritDao ploegenTijdritDao;
	private EindUitslagDao eindUitslagDao;
	private UitslagBedragDao uitslagBedragDao;

	public TourFacadeImpl() {
	}

	/**
	 * Geeft de deelnemers met hun renners terug
	 * 
	 * @return De deelnemers met hun renners
	 */
	public List<DeelnemerWithRennersDto> getAllDeelnemersAndRenners() {
		// Laadt alle deelnemers Eager uit de database
		List<Deelnemer> deelnemers = deelnemerDao.loadAllDeelnemersEager();

		List<DeelnemerWithRennersDto> allDeelnemersAndRenners = new ArrayList<DeelnemerWithRennersDto>();
		for (Deelnemer nextDeelnemer : deelnemers) {
			DeelnemerWithRennersDto deelnemer = new DeelnemerWithRennersDto();
			deelnemer.setNummer(nextDeelnemer.getNummer());
			deelnemer.setAchternaam(nextDeelnemer.getAchternaam());
			deelnemer.setVoornaam(nextDeelnemer.getVoornaam());
			deelnemer.setEmail(nextDeelnemer.getEmail());
			deelnemer.setRekeningnummer(nextDeelnemer.getRekeningnummer());
			deelnemer.setRenners(new TreeSet<RennerDto>(new RennerComparator()));
			// Nu renners in DTO kopieren
			for (Renner nextRenner : nextDeelnemer.getRenners()) {
				RennerDto renner = new RennerDto();
				renner.setNummer(nextRenner.getNummer());
				renner.setAchternaam(nextRenner.getAchternaam());
				renner.setVoornaam(nextRenner.getVoornaam());
				deelnemer.getRenners().add(renner);
			}

			// deelnemerAndRenners = (List<DeelnemerWithRennersDto>)
			// mapper.map(nextDeelnemer, java.util.List.class);
			allDeelnemersAndRenners.add(deelnemer);
		}
		return allDeelnemersAndRenners;
	}

	private class RennerComparator implements Comparator<RennerDto> {

		public int compare(RennerDto o1, RennerDto o2) {
			int comparison = 0;
			// TODO Is het nog nodig om deze check te doen (met het oog op type erasure at runtime).
			if (o1 instanceof RennerDto && o2 instanceof RennerDto) {
				// Als 1 van de twee een kopman is dan is dat altijd de kleinste
				if (((RennerDto) o1).getNummer() % 10 == 1) {
					comparison = -1;
				} else if (((RennerDto) o2).getNummer() % 10 == 1) {
					comparison = 1;
				} else if (((RennerDto) o1).getNummer() > ((RennerDto) o2).getNummer()) {
					comparison = 1;
				} else if (((RennerDto) o1).getNummer() < ((RennerDto) o2).getNummer()) {
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
	 * @param etappeNummer
	 *            Het nummer van de etappe
	 * @return Etappe
	 */
	public AbstractEtappeAndEindUitslag getEtappeWithUitslag(int etappeNummer) {
		AbstractEtappeAndEindUitslag etappe;
		if (etappeNummer == 0) {
			etappe = eindUitslagDao.loadEindUitslagWithUitslagEager();
		} else {
			// Haal de etappe op uit de db. Als het geen standaardetappe is,
			// haal dan een ploegentijdrit op.
			etappe = standaardEtappeDao.loadStandaardEtappeWithUitslagEager(etappeNummer);
		}

		if (etappe == null) {
			etappe = ploegenTijdritDao.loadPloegenTijdritWithUitslagEager(etappeNummer);
		}
		return etappe;
	}

	public AbstractEtappeAndEindUitslagDto getEtappeDtoWithUitslag(int etappeNummer) {
		AbstractEtappeAndEindUitslag etappe = getEtappeWithUitslag(etappeNummer);
		AbstractEtappeAndEindUitslagDto etappeDto = mapAbstractEtappeAndEindUitslagWithUitslagToDto(etappe);
		return etappeDto;
	}

	private AbstractEtappeAndEindUitslagDto initDto(AbstractEtappeAndEindUitslag etappe) {
		AbstractEtappeAndEindUitslagDto dto = null;

		if (etappe instanceof StandaardEtappe) {
			dto = new StandaardEtappeDto();
		} else if (etappe instanceof PloegenTijdrit) {
			dto = new PloegenTijdritDto();
		} else if (etappe instanceof EindUitslag) {
			dto = new EindUitslagDto();
		} else {
			throw new IllegalArgumentException("Etappe niet van het juiste type");
		}
		dto.setEtappenummer(etappe.getEtappenummer());
		dto.setOmschrijving(etappe.getOmschrijving());
		return dto;

	}

	private AbstractEtappeAndEindUitslagDto mapAbstractEtappeAndEindUitslagWithUitslagToDto(AbstractEtappeAndEindUitslag etappe) {
		AbstractEtappeAndEindUitslagDto dto = initDto(etappe);

		if (etappe instanceof Etappe) {
			dto.setGeleTruiUitslag(mapUitslagToDto(etappe.getGeleTruiUitslag(), Categorien.GeleTrui));
			dto.setGroeneTruiUitslag(mapUitslagToDto(etappe.getGroeneTruiUitslag(), Categorien.GroeneTrui));
			dto.setBolletjesTruiUitslag(mapUitslagToDto(etappe.getBolletjesTruiUitslag(), Categorien.BolletjesTrui));

			Set<Uitslag> mostCombative = new HashSet<Uitslag>();
			if (etappe.getMostCombativeRacer() != null) {
				GeleTruiUitslag mostCombativeResult = new GeleTruiUitslag();
				mostCombativeResult.setPositie(1);
				mostCombativeResult.setEtappenummer(etappe.getEtappenummer());
				mostCombativeResult.setRenner(((Etappe) etappe).getMostCombativeRacer());
				mostCombative.add(mostCombativeResult);
			}
			dto.setMostCombativeResult(mapUitslagToDto(mostCombative, Categorien.MostCombativeStage));
			
			
			if (etappe instanceof StandaardEtappe) {
				((StandaardEtappeDto) dto).setEtappeUitslag(mapUitslagToDto(((StandaardEtappe) etappe).getEtappeUitslag(),
						Categorien.Etappe));
			} else if (etappe instanceof PloegenTijdrit) {
				dto = new PloegenTijdritDto();
			} else {
				throw new IllegalArgumentException("Etappe niet van het juiste type");
			}
		} else if (etappe instanceof EindUitslag) {
			// TODO Dit moet een stuk netter geimplementeerd worden, zitten nu om het probleem heen te werken
			dto.setGeleTruiUitslag(mapUitslagToDto(etappe.getGeleTruiUitslag(), Categorien.GeleTruiEind));
			dto.setGroeneTruiUitslag(mapUitslagToDto(etappe.getGroeneTruiUitslag(), Categorien.GroeneTruiEind));
			dto.setBolletjesTruiUitslag(mapUitslagToDto(etappe.getBolletjesTruiUitslag(), Categorien.BolletjesTruiEind));

			Set<Uitslag> eersteUitvaller = new HashSet<Uitslag>();
			if (((EindUitslag) etappe).getEersteUitvaller() != null) {
				GeleTruiUitslag eersteUitvallerUitslag = new GeleTruiUitslag();
				eersteUitvallerUitslag.setPositie(1);
				eersteUitvallerUitslag.setEtappenummer(etappe.getEtappenummer());
				eersteUitvallerUitslag.setRenner(((EindUitslag) etappe).getEersteUitvaller());
				eersteUitvaller.add(eersteUitvallerUitslag);
			}
			((EindUitslagDto) dto).setEersteUitvaller(mapUitslagToDto(eersteUitvaller, Categorien.EersteUitvallerEind));

			Set<Uitslag> rodeLantaren = new HashSet<Uitslag>();
			if (((EindUitslag) etappe).getRodeLantaren() != null) {
				GeleTruiUitslag rodeLantarenUitslag = new GeleTruiUitslag();
				rodeLantarenUitslag.setPositie(1);
				rodeLantarenUitslag.setEtappenummer(etappe.getEtappenummer());
				rodeLantarenUitslag.setRenner(((EindUitslag) etappe).getRodeLantaren());
				rodeLantaren.add(rodeLantarenUitslag);
			}
			((EindUitslagDto) dto).setRodeLantaren(mapUitslagToDto(rodeLantaren, Categorien.RodeLantarenEind));

			Set<Uitslag> witteTrui = new HashSet<Uitslag>();
			if (((EindUitslag) etappe).getWitteTrui() != null) {
				GeleTruiUitslag witteTruiUitslag = new GeleTruiUitslag();
				witteTruiUitslag.setPositie(1);
				witteTruiUitslag.setEtappenummer(etappe.getEtappenummer());
				witteTruiUitslag.setRenner(((EindUitslag) etappe).getWitteTrui());
				witteTrui.add(witteTruiUitslag);
			}
			((EindUitslagDto) dto).setWitteTrui(mapUitslagToDto(witteTrui, Categorien.WitteTruiEind));
			
			Set<Uitslag> mostCombative = new HashSet<Uitslag>();
			if (etappe.getMostCombativeRacer() != null) {
				GeleTruiUitslag mostCombativeResult = new GeleTruiUitslag();
				mostCombativeResult.setPositie(1);
				mostCombativeResult.setEtappenummer(etappe.getEtappenummer());
				mostCombativeResult.setRenner(((EindUitslag) etappe).getMostCombativeRacer());
				mostCombative.add(mostCombativeResult);
			}
			dto.setMostCombativeResult(mapUitslagToDto(mostCombative, Categorien.MostCombativeFinal));
			
		} else {
			throw new IllegalArgumentException("Etappe niet van het juiste type");
		}

		return dto;
	}

	private Set<UitslagDto> mapUitslagToDto(Set<? extends Uitslag> uitslag, Categorien categorie) {
		Set<UitslagDto> uitslagDto = new HashSet<UitslagDto>();
		Locale dutch = new Locale("nl", "NL");
		NumberFormat format = NumberFormat.getInstance(dutch);
		format.setMinimumFractionDigits(2);

		for (Uitslag nextUitslag : uitslag) {
			UitslagDto dto = new UitslagDto();
			dto.setPositie(nextUitslag.getPositie());
			UitslagBedrag bedrag = uitslagBedragDao.loadUitslagBedrag(categorie, nextUitslag.getPositie());
			if (bedrag != null) {
				dto.setPositieBedrag(format.format(bedrag.getBedrag()));
			} else {
				dto.setPositieBedrag("");
			}
			RennerDto rennerDto = new RennerDto();
			rennerDto.setNummer(nextUitslag.getRenner().getNummer());
			rennerDto.setVoornaam(nextUitslag.getRenner().getVoornaam());
			rennerDto.setAchternaam(nextUitslag.getRenner().getAchternaam());
			dto.setRenner(rennerDto);

			DeelnemerDto deelnemerDto = new DeelnemerDto();
			Deelnemer deelnemer = deelnemerDao.loadDeelnemerHavingRenner(nextUitslag.getRenner());
			deelnemerDto.setNummer(deelnemer.getNummer());
			deelnemerDto.setAchternaam(deelnemer.getAchternaam());
			deelnemerDto.setVoornaam(deelnemer.getVoornaam());
			dto.setDeelnemer(deelnemerDto);
			uitslagDto.add(dto);
		}
		return uitslagDto;
	}

	/**
	 * Geeft de opgevraagde etappe terug inclusief start- en finishplaats
	 * 
	 * 
	 * @param etappeNummer
	 *            Het nummer van de etappe
	 * @return Etappe
	 */
	public Etappe getEtappeWithStartAndFinish(int etappeNummer) {
		Etappe etappe = standaardEtappeDao.loadStandaardEtappeWithStartAndFinish(etappeNummer);
		if (etappe == null) {
			etappe = ploegenTijdritDao.loadPloegenTijdritWithStartAndFinish(etappeNummer);
		}
		return etappe;
	}

	public AbstractEtappeAndEindUitslag getEtappe(int etappeNummer) {
		AbstractEtappeAndEindUitslag etappe;
		if (etappeNummer == 0) {
			etappe = eindUitslagDao.loadEindUitslag();
		} else
			etappe = standaardEtappeDao.loadStandaardEtappe(etappeNummer);
		if (etappe == null) {
			etappe = ploegenTijdritDao.loadPloegenTijdrit(etappeNummer);
		}
		return etappe;
	}

	// TODO This can be easily solved using dynamic binding. Just create an EtappeDao with 3 overloaded methods, one for each etappe type.
	// TODO That won't work with overloaded methods, because it is determined at compile time.
	public void saveEtappe(AbstractEtappeAndEindUitslag etappe) {
		if (etappe instanceof StandaardEtappe) {
			standaardEtappeDao.saveStandaardEtappe((StandaardEtappe) etappe);
		} else if (etappe instanceof PloegenTijdrit) {
			ploegenTijdritDao.savePloegenTijdrit((PloegenTijdrit) etappe);
		} else if (etappe instanceof EindUitslag) {
			eindUitslagDao.saveEindUitslag((EindUitslag) etappe);
		} else {
			throw new RuntimeException("Etappe kan niet worden opgeslagen. Etappe is van het verkeerde type.");
		}
	}

	/**
	 * Bepaalt het hoogste etappenummer in de DB.
	 * 
	 * @return etappenummer
	 */
	public int getHighestEtappeNummer() {
		int ploegenTijdritNummer = ploegenTijdritDao.getHighestEtappeNummer();
		int standaardEtappeNummer = standaardEtappeDao.getHighestEtappeNummer();
		int nummer = 0;
		if (standaardEtappeNummer > ploegenTijdritNummer) {
			nummer = standaardEtappeNummer;
		} else if (ploegenTijdritNummer > standaardEtappeNummer) {
			nummer = ploegenTijdritNummer;
		} else {
			throw new IllegalStateException("Fout bij het bepalen van het hoogste etappenummer.");
		}

		return nummer;
	}

	/**
	 * @return the deelnemerDao
	 */
	public DeelnemerDao getDeelnemerDao() {
		return deelnemerDao;
	}

	/**
	 * @param deelnemerDao
	 *            the deelnemerDao to set
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
	 * @param ploegenTijdritDao
	 *            the ploegenTijdritDao to set
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
	 * @param standaardEtappeDao
	 *            the standaardEtappeDao to set
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
	 * @param eindUitslagDao
	 *            the eindUitslagDao to set
	 */
	public void setEindUitslagDao(EindUitslagDao eindUitslagDao) {
		this.eindUitslagDao = eindUitslagDao;
	}

	public UitslagBedragDao getUitslagBedragDao() {
		return uitslagBedragDao;
	}

	public void setUitslagBedragDao(UitslagBedragDao uitslagBedragDao) {
		this.uitslagBedragDao = uitslagBedragDao;
	}

}
