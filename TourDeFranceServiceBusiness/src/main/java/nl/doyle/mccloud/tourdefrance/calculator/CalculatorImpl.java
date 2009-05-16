package nl.doyle.mccloud.tourdefrance.calculator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import nl.doyle.mccloud.tourdefrance.config.TourConfig;
import nl.doyle.mccloud.tourdefrance.controller.TourFacade;
import nl.doyle.mccloud.tourdefrance.dao.DeelnemerDao;
import nl.doyle.mccloud.tourdefrance.dao.EindUitslagDao;
import nl.doyle.mccloud.tourdefrance.dao.PloegenTijdritDao;
import nl.doyle.mccloud.tourdefrance.dao.StandaardEtappeDao;
import nl.doyle.mccloud.tourdefrance.dao.UitslagBedragDao;
import nl.doyle.mccloud.tourdefrance.dto.DeelnemerBedragDto;
import nl.doyle.mccloud.tourdefrance.exceptions.DataNotFoundException;
import nl.doyle.mccloud.tourdefrance.valueobjects.AbstractEtappeAndEindUitslag;
import nl.doyle.mccloud.tourdefrance.valueobjects.Deelnemer;
import nl.doyle.mccloud.tourdefrance.valueobjects.EindUitslag;
import nl.doyle.mccloud.tourdefrance.valueobjects.Etappe;
import nl.doyle.mccloud.tourdefrance.valueobjects.Renner;
import nl.doyle.mccloud.tourdefrance.valueobjects.StandaardEtappe;
import nl.doyle.mccloud.tourdefrance.valueobjects.UitslagBedrag;
import nl.doyle.mccloud.tourdefrance.valueobjects.UitslagBedrag.Categorien;

import org.apache.commons.lang.IllegalClassException;

/**
 * Utility klasse die de gewonnen bedragen per deelnemer uitrekent.
 * 
 * @author idxdoadmin
 */
public class CalculatorImpl implements Calculator {
	
	private DeelnemerDao deelnemerDao;
	private EindUitslagDao eindUitslagDao;
	private StandaardEtappeDao standaardEtappeDao;
	private PloegenTijdritDao ploegenTijdritDao;
	private UitslagBedragDao uitslagBedragDao;
	private TourFacade tourFacade;
	private TourConfig tourConfig;

	
	/**
	 * Default Constructor
	 */
	public CalculatorImpl() {
	}
	
	
	
	
	public List<DeelnemerBedragDto> getAllDeelnemersAndGewonnenBedragAfterEtappe(int etappenummer) {
		List<DeelnemerBedragDto> deelnemersAndBedragen;
		//Etappenummer 0 is het etappenummer van de einduitslag
		if (etappenummer == 0) {
			deelnemersAndBedragen = getAllDeelnemersAndBedragEindUitslag();
		} else {
			deelnemersAndBedragen = getAllDeelnemersAndBedragAfterEtappe(etappenummer);
		}
		return deelnemersAndBedragen;
	}




	/**
	 * Geeft een lijst van DeelnemerBedragDto's terug met bedragen t/m de opgegeven etappe.
	 */
	private List<DeelnemerBedragDto> getAllDeelnemersAndBedragAfterEtappe(
			int etappenummer) {
		// TODO Botte bijl implementatie. Moet nog geoptimaliseerd worden.
		//TODO Checken of etappenummer tussen min en max etappe inzit
		
		if ((etappenummer < 1) || (etappenummer > tourFacade.getHighestEtappeNummer())) {
			throw new IllegalArgumentException("Etappenummer niet aanwezig in de database");
		}
		//Initialiseer de return ArrayList
		List<DeelnemerBedragDto> deelnemersMetBedragen = new ArrayList<DeelnemerBedragDto>();
		
		//Laadt de uitslagbedragen uit de db en stop ze in double arrays
		double[] etappeUitslagBedrag = getEtappeUitslagBedragenPerPositie();
		double[] etappeGeleTruiUitslagBedrag = getEtappeGeleTruiUitslagBedragenPerPositie();
		double[] etappeGroeneTruiUitslagBedrag = getEtappeGroeneTruiUitslagBedragenPerPositie();
		double[] etappeBolletjesTruiUitslagBedrag = getEtappeBolletjesTruiUitslagBedragenPerPositie();
		double[] stageMostCombativeRacerResultAmount = getStageMostCombativeRacerResultAmount();
		
		//Maak HashMap om bedragen per renner in op te slaan. Integer is het rennernummer, Double het bedrag
		HashMap<Integer, Double> bedragenMap = new HashMap<Integer, Double>();
		List<Deelnemer> deelnemers = deelnemerDao.loadAllDeelnemersEager();
		//TODO Het bijhouden van gewonnen bedragen kan ook in een array. Dit is misschien sneller. Dan moet het alleen wel zeker zijn dat er2x op exact dezelfde manier over de deelnemerslijst wordt geittereerd.
		
		//Loop door alle etappes
		for (int etappeTeller = 1; etappeTeller <= etappenummer; etappeTeller++) {
			//Laad de etappe uit de DB
			AbstractEtappeAndEindUitslag nextEtappe = tourFacade.getEtappeWithUitslag(etappeTeller);
			if (nextEtappe == null) { 
				DataNotFoundException dnf = new DataNotFoundException("Etappe with number: " + etappeTeller + ". Etappe numbers not consecutive or entered value is too high.");
				throw new RuntimeException(dnf);
			}
			if (nextEtappe instanceof Etappe) {
				//Loop door alle Deelnemers
				for (Deelnemer nextDeelnemer : deelnemers) {
					double deelnemerBedragEtappe = 0;
					//Loop door renners
					Set<Renner> renners = nextDeelnemer.getRenners();
					for (Renner nextRenner : renners) {
						//Nu voor deze renner bepalen welke bedragen hij heeft gewonnen in deze etappe.
						//GeleTrui
						int positie = nextEtappe.getPositieInGeleTruiUitslag(nextRenner);
						if (positie != 0) {
							deelnemerBedragEtappe = deelnemerBedragEtappe + etappeGeleTruiUitslagBedrag[positie - 1];
						}
						//GroeneTrui
						positie = nextEtappe.getPositieInGroeneTruiUitslag(nextRenner);
						if (positie != 0) {
							deelnemerBedragEtappe = deelnemerBedragEtappe + etappeGroeneTruiUitslagBedrag[positie - 1];
						}
						//BolletjesTrui
						positie = nextEtappe.getPositieInBolletjesTruiUitslag(nextRenner);
						if (positie != 0) {
							deelnemerBedragEtappe = deelnemerBedragEtappe + etappeBolletjesTruiUitslagBedrag[positie - 1];
						}
						//Most combative race
						positie = nextEtappe.getPositionInMostCombativeRacerResult(nextRenner);
						if (positie != 0) {
							deelnemerBedragEtappe = deelnemerBedragEtappe + stageMostCombativeRacerResultAmount[positie - 1];
						}
						
						//Als het een standaardetappe is dan ook nog bedragen voor etappe uitslag
						if (nextEtappe instanceof StandaardEtappe) {
							positie = ((StandaardEtappe) nextEtappe).getPositieInEtappeUitslag(nextRenner);
							if (positie != 0) {
								deelnemerBedragEtappe = deelnemerBedragEtappe + etappeUitslagBedrag[positie - 1];
							}
						}
					}
					
					//Nu moet het bedrag dat deze deelnemer in deze etappe heeft gewonnen opgeslagen worden. Dit doen we nu in een HashMap
					//We tellen het gewonnenbedrag uit deze etappe op bij het bedrag uit de vorige etappes.
					Double currentBedrag = bedragenMap.get(nextDeelnemer.getNummer());
					//Als er al eerder een bedrag voor deze deelnemer was gezet tellen we dat op bij het bedrag dat in deze etappe is gewonnen.
					if (currentBedrag != null) {
						deelnemerBedragEtappe = deelnemerBedragEtappe + currentBedrag;
					}
					bedragenMap.put(nextDeelnemer.getNummer(), deelnemerBedragEtappe);
				}
			} else {
				throw new IllegalClassException("Etappe is niet van het juiste type");
			}
		}
		
		//Maak nu de DTOs aan
		//Zet eerste alle deelnemers in de lijst met DeelnemerBedragDto's
		
		//Lijst met deelnemers waar doorheen gelooped wordt is eerder al gezet.
		int bedragenArrayTeller = 0;
		for (Deelnemer nextDeelnemer : deelnemers) {
			DeelnemerBedragDto deelnemerBedrag = new DeelnemerBedragDto();
			deelnemerBedrag.setNummer(nextDeelnemer.getNummer());
			deelnemerBedrag.setAchternaam(nextDeelnemer.getAchternaam());
			deelnemerBedrag.setVoornaam(nextDeelnemer.getVoornaam());
			deelnemerBedrag.setEmail(nextDeelnemer.getEmail());
			deelnemerBedrag.setRekeningnummer(nextDeelnemer.getRekeningnummer());
			//Zet nu het gewonnen bedrag uit de hashmap bij het bedrag
			deelnemerBedrag.setGewonnenBedrag(bedragenMap.get(nextDeelnemer.getNummer()));
			deelnemersMetBedragen.add(deelnemerBedrag);
			bedragenArrayTeller++;
		}
		return deelnemersMetBedragen;
	}
	
	
	/**
	 * Geeft een lijst van DeelnemerBedragDto's t/m het einde van de Tour
	 * 
	 * @return Lijst van DeelnemerBedragDto's 
	 */
	private List<DeelnemerBedragDto> getAllDeelnemersAndBedragEindUitslag() {
		double[] eindUitslagWitteTruiUitslagBedrag = getEindUitslagWitteTruiUitslagBedrag();
		double[] eindUitslagRodeLantarenUitslagBedrag = getEindUitslagRodeLantarenUitslagBedrag();
		double[] eindUitslagEersteUitvallerUitslagBedrag = getEindUitslagEersteUitvallerUitslagBedrag();
		double[] finalMostCombativeRacerResultAmount = getFinalMostCombativeRacerResultAmount();
		
		double[] eindUitslagBolletjesTruiUitslagBedrag = getEindUitslagBolletjesTruiUitslagBedragPerPositie();
		double[] eindUitslagGeleTruiUitslagBedrag = getEindUitslagGeleTruiUitslagBedragenPerPositie();
		double[] eindUitslagGroeneTruiUitslagBedrag = getEindUitslagGroeneTruiUitslagBedragenPerPositie();
		
		
		//Bepaal het nummer van de laatste etappe en laad de deelnemers en bedragen van alle etappes
		int etappenummer = tourFacade.getHighestEtappeNummer();
		List<DeelnemerBedragDto> deelnemersBedragEtappe = getAllDeelnemersAndBedragAfterEtappe(etappenummer);
		
		
		List<DeelnemerBedragDto> deelnemerAndBedrag = new ArrayList<DeelnemerBedragDto>();
		List<Deelnemer> deelnemers = deelnemerDao.loadAllDeelnemersEager();
		for (Deelnemer nextDeelnemer : deelnemers) {
			DeelnemerBedragDto dbDto = new DeelnemerBedragDto();
			dbDto.setNummer(nextDeelnemer.getNummer());
			dbDto.setVoornaam(nextDeelnemer.getVoornaam());
			dbDto.setAchternaam(nextDeelnemer.getAchternaam());
			dbDto.setRekeningnummer(nextDeelnemer.getRekeningnummer());
			dbDto.setEmail(nextDeelnemer.getEmail());
			double bedrag = 0;
			int positie = 0;
			AbstractEtappeAndEindUitslag uitslag = tourFacade.getEtappeWithUitslag(0);
			if (uitslag instanceof EindUitslag) {
				for (Renner nextRenner : nextDeelnemer.getRenners()) {
					positie = uitslag.getPositieInGeleTruiUitslag(nextRenner);
					if (positie != 0) {
						bedrag = bedrag + eindUitslagGeleTruiUitslagBedrag[positie - 1];
					}
					positie = uitslag.getPositieInGroeneTruiUitslag(nextRenner);
					if (positie != 0) {
						bedrag = bedrag + eindUitslagGroeneTruiUitslagBedrag[positie - 1];
					}
					positie = uitslag.getPositieInBolletjesTruiUitslag(nextRenner);
					if (positie != 0) {
						bedrag = bedrag + eindUitslagBolletjesTruiUitslagBedrag[positie - 1];
					}
					positie = ((EindUitslag) uitslag).getPositieInWitteTruiUitslag(nextRenner);
					if (positie != 0) {
						bedrag = bedrag + eindUitslagWitteTruiUitslagBedrag[positie - 1];
					}
					positie = ((EindUitslag) uitslag).getPositieInRodeLantarenUitslag(nextRenner);
					if (positie != 0) {
						bedrag = bedrag + eindUitslagRodeLantarenUitslagBedrag[positie - 1];
					}
					positie = ((EindUitslag) uitslag).getPositieInEersteUitvallerUitslag(nextRenner);
					if (positie != 0) {
						bedrag = bedrag + eindUitslagEersteUitvallerUitslagBedrag[positie - 1];
					}
					positie = ((EindUitslag) uitslag).getPositionInMostCombativeRacerResult(nextRenner);
					if (positie != 0) {
						bedrag = bedrag + finalMostCombativeRacerResultAmount[positie - 1];
					}
				}
			} else {
				throw new IllegalClassException("EindUitslag is niet van het juiste type");
			}
			//Bepaal nu het bedrag van alle etappes.
			for (DeelnemerBedragDto nextDeelnemerBedrag : deelnemersBedragEtappe) {
				if (nextDeelnemer.getNummer() == nextDeelnemerBedrag.getNummer()) {
					bedrag = bedrag +  nextDeelnemerBedrag.getGewonnenBedrag();
				}
			}
			dbDto.setGewonnenBedrag(bedrag);
			deelnemerAndBedrag.add(dbDto);
		}
		return deelnemerAndBedrag;
	}
	
	private double[] getEtappeUitslagBedragenPerPositie() {
		return setBedragen(tourConfig.getAantalEtappeUitslagen(), uitslagBedragDao.loadAllUitslagBedragenPerCategorie(Categorien.Etappe));
	}
	
	private double[] getEtappeGeleTruiUitslagBedragenPerPositie() {
		return setBedragen(tourConfig.getAantalEtappeGeleTruiUitslagen(), uitslagBedragDao.loadAllUitslagBedragenPerCategorie(Categorien.GeleTrui));
	}
	
	private double[] getEtappeGroeneTruiUitslagBedragenPerPositie() {
		return setBedragen(tourConfig.getAantalEtappeGroeneTruiUitslagen(), uitslagBedragDao.loadAllUitslagBedragenPerCategorie(Categorien.GroeneTrui));
	}
	
	private double[] getEtappeBolletjesTruiUitslagBedragenPerPositie() {
		return setBedragen(tourConfig.getAantalEtappeBolletjesTruiUitslagen(), uitslagBedragDao.loadAllUitslagBedragenPerCategorie(Categorien.BolletjesTrui));
	}
	
	private double[] getStageMostCombativeRacerResultAmount() {
		return setBedragen(tourConfig.getAantalEinduitslagWitteTrui(), uitslagBedragDao.loadAllUitslagBedragenPerCategorie(Categorien.MostCombativeStage));
	}
	
	private double[] getEindUitslagGeleTruiUitslagBedragenPerPositie() {
		return setBedragen(tourConfig.getAantalEinduitslagGeleTruiUitslagen(), uitslagBedragDao.loadAllUitslagBedragenPerCategorie(Categorien.GeleTruiEind));
	}
	
	private double[] getEindUitslagGroeneTruiUitslagBedragenPerPositie() {
		return setBedragen(tourConfig.getAantalEinduitslagGroeneTruiUitslagen(), uitslagBedragDao.loadAllUitslagBedragenPerCategorie(Categorien.GroeneTruiEind));
	}
	
	private double[] getEindUitslagBolletjesTruiUitslagBedragPerPositie() {
		return setBedragen(tourConfig.getAantalEinduitslagBolletjesTruiUitslagen(), uitslagBedragDao
				.loadAllUitslagBedragenPerCategorie(Categorien.BolletjesTruiEind));
	}
	
	private double[] getEindUitslagWitteTruiUitslagBedrag() {
		return setBedragen(tourConfig.getAantalEinduitslagWitteTrui(), uitslagBedragDao.loadAllUitslagBedragenPerCategorie(Categorien.WitteTruiEind));
	}
	
	private double[] getEindUitslagRodeLantarenUitslagBedrag() {
		return setBedragen(tourConfig.getAantalEinduitslagRodeLantaren(), uitslagBedragDao.loadAllUitslagBedragenPerCategorie(Categorien.RodeLantarenEind));
	}
	
	private double[] getEindUitslagEersteUitvallerUitslagBedrag() {
		return setBedragen(tourConfig.getAantalEinduitslagEersteUitvaller(), uitslagBedragDao.loadAllUitslagBedragenPerCategorie(Categorien.EersteUitvallerEind));
	}
	
	private double[] getFinalMostCombativeRacerResultAmount() {
		return setBedragen(tourConfig.getAantalEinduitslagWitteTrui(), uitslagBedragDao.loadAllUitslagBedragenPerCategorie(Categorien.MostCombativeFinal));
	}
	
	private double[] setBedragen(int arrayPosities, List<UitslagBedrag> bedragen) {
		double[] bedragarray = new double[arrayPosities];
		for (UitslagBedrag nextBedrag : bedragen) {
			if (nextBedrag.getPositie() <= bedragarray.length) {
				bedragarray[nextBedrag.getPositie() - 1] = nextBedrag.getBedrag();
			}
		}
		return bedragarray;
	}

	
	/**
	 * Geeft de DeelnemerBedragDto terug met het gewonnen bedrag t/m/ de opgegeven etappe.
	 * 
	 * @param deelnemernummer Het nummer van de deelnemer
	 * @param etappenummer Het nummer van de etappe
	 * 
	 * @result DeelnemerBedragDto
	 */
	public DeelnemerBedragDto getDeelnemerAndBedragAfterEtappe(
			int deelnemernummer, int etappenummer) {
		
		return null;
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
	 * @return the uitslagBedragDao
	 */
	public UitslagBedragDao getUitslagBedragDao() {
		return uitslagBedragDao;
	}

	/**
	 * @param uitslagBedragDao the uitslagBedragDao to set
	 */
	public void setUitslagBedragDao(UitslagBedragDao uitslagBedragDao) {
		this.uitslagBedragDao = uitslagBedragDao;
	}


	/**
	 * @return the tourFacade
	 */
	public TourFacade getTourFacade() {
		return tourFacade;
	}


	/**
	 * @param tourFacade the tourFacade to set
	 */
	public void setTourFacade(TourFacade tourFacade) {
		this.tourFacade = tourFacade;
	}


	/**
	 * @return the tourConfig
	 */
	public TourConfig getTourConfig() {
		return tourConfig;
	}


	/**
	 * @param tourConfig the tourConfig to set
	 */
	public void setTourConfig(TourConfig tourConfig) {
		this.tourConfig = tourConfig;
	}

	
	
	
}
