package nl.doyle.mccloud.tourdefrance.calculator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
import nl.doyle.mccloud.tourdefrance.dto.DeelnemerDto;
import nl.doyle.mccloud.tourdefrance.exceptions.DataNotFoundException;
import nl.doyle.mccloud.tourdefrance.valueobjects.AbstractEtappeAndEindUitslag;
import nl.doyle.mccloud.tourdefrance.valueobjects.Deelnemer;
import nl.doyle.mccloud.tourdefrance.valueobjects.Etappe;
import nl.doyle.mccloud.tourdefrance.valueobjects.Renner;
import nl.doyle.mccloud.tourdefrance.valueobjects.StandaardEtappe;
import nl.doyle.mccloud.tourdefrance.valueobjects.Uitslag;
import nl.doyle.mccloud.tourdefrance.valueobjects.UitslagBedrag;
import nl.doyle.mccloud.tourdefrance.valueobjects.UitslagBedrag.Categorien;

import org.apache.commons.lang.IllegalClassException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Utility klasse die de gewonnen bedragen per deelnemer uitrekent.
 * 
 * @author idxdoadmin
 */
public class CalculatorImpl implements Calculator {
	
	private static final Log logger = LogFactory.getLog(CalculatorImpl.class);
	
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
	
	
	/**
	 * Geeft een lijst van DeelnemerBedragDto's terug met bedragen t/m de opgegeven etappe.
	 */
	public List<DeelnemerBedragDto> getAllDeelnemersAndBedragAfterEtappe(
			int etappenummer) {
		// TODO Botte bijl implementatie. Moet nog geoptimaliseerd worden.
		
		//Laadt de uitslagbedragen uit de db en stop ze in double arrays
		double[] etappeUitslagBedrag = getEtappeUitslagBedragenPerPositie();
		double[] etappeGeleTruiUitslagBedrag = getEtappeGeleTruiUitslagBedragenPerPositie();
		double[] etappeGroeneTruiUitslagBedrag = getEtappeGroeneTruiUitslagBedragenPerPositie();
		double[] etappeBolletjesTruiUitslagBedrag = getEtappeBolletjesTruiUitslagBedragenPerPositie();
		
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
				for (Deelnemer nextDeelnemer: deelnemers) {
					double deelnemerBedragEtappe = 0;
					//Loop door renners
					Set<Renner> renners = nextDeelnemer.getRenners();
					for (Renner nextRenner: renners) {
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
		List<DeelnemerBedragDto> deelnemersMetBedragen = new ArrayList<DeelnemerBedragDto>();
		//Lijst met deelnemers waar doorheen gelooped wordt is eerder al gezet.
		int bedragenArrayTeller = 0;
		for (Deelnemer nextDeelnemer: deelnemers) {
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
	public List<DeelnemerBedragDto> getAllDeelnemersAndBedragAtEnd() {
		// TODO Auto-generated method stub
		//		TODO De waardes voor witteTrui, rodeLantaren en eersteUitvaller moeten nog uit de DB worden opgehaald.
		double witteTrui;
		double rodeLantaren;
		double eersteUitvaller;
		
		double[] eindUitslagBolletjesTruiUitslagBedrag = getEtappeBolletjesTruiUitslagBedragenPerPositie();
		double[] eindUitslagGeleTruiUitslagBedrag = getEindUitslagGeleTruiUitslagBedragenPerPositie();
		double[] eindUitslagGroeneTruiUitslagBedrag = getEindUitslagGroeneTruiUitslagBedragenPerPositie();
		
		
		return null;
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
	
	private double[] getEindUitslagGeleTruiUitslagBedragenPerPositie() {
		return setBedragen(tourConfig.getAantalEinduitslagGeleTruiUitslagen(), uitslagBedragDao.loadAllUitslagBedragenPerCategorie(Categorien.GeleTruiEind));
	}
	
	private double[] getEindUitslagGroeneTruiUitslagBedragenPerPositie() {
		return setBedragen(tourConfig.getAantalEinduitslagGroeneTruiUitslagen(), uitslagBedragDao.loadAllUitslagBedragenPerCategorie(Categorien.GroeneTruiEind));
	}
	
	private double[] getEindUitslagBolletjesTruiUitslagBedragPerPositie() {
		return setBedragen(tourConfig.getAantalEinduitslagBolletjesTruiUitslagen(),uitslagBedragDao.loadAllUitslagBedragenPerCategorie(Categorien.BolletjesTruiEind));
	}
	
	private double[] loadEindUitslagWitteTruiUitslagBedrag(List<UitslagBedrag> bedragen) {
		return setBedragen(tourConfig.getAantalEinduitslagWitteTrui(), bedragen);
	}
	
	private double[] loadEindUitslagRodeLantarenUitslagBedrag(List<UitslagBedrag> bedragen) {
		return setBedragen(tourConfig.getAantalEinduitslagRodeLantaren(), bedragen);
	}
	
	private double[] loadEindUitslagEersteUitvallerUitslagBedrag(List<UitslagBedrag> bedragen) {
		return setBedragen(tourConfig.getAantalEinduitslagEersteUitvaller(), bedragen);
	}
	
	private double[] setBedragen(int arrayPosities, List<UitslagBedrag> bedragen) {
		double[] bedragarray = new double[arrayPosities];
		for (UitslagBedrag nextBedrag:bedragen) {
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
