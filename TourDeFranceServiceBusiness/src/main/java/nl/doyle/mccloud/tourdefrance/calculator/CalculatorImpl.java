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
		double[] etappeUitslagBedrag = loadEtappeUitslagBedrag(uitslagBedragDao.loadAllUitslagBedragenPerCategorie(Categorien.Etappe));
		double[] etappeGeleTruiUitslagBedrag = loadEtappeGeleTruiUitslagBedrag(uitslagBedragDao.loadAllUitslagBedragenPerCategorie(Categorien.GeleTrui));
		double[] etappeGroeneTruiUitslagBedrag = loadEtappeGroeneTruiUitslagBedrag(uitslagBedragDao.loadAllUitslagBedragenPerCategorie(Categorien.GroeneTrui));
		
		//Maak HashMap om bedragen per renner in op te slaan. Integer is het rennernummer, Double het bedrag
		HashMap<Integer, Double> bedragenMap = new HashMap<Integer, Double>();
		List<Deelnemer> deelnemers = deelnemerDao.loadAllDeelnemersEager();
		//TODO Het bijhouden van gewonnen bedragen kan ook in een array. Dit is misschien sneller. Dan moet het alleen wel zeker zijn dat er2x op exact dezelfde manier over de deelnemerslijst wordt geittereerd.
		
		//Loop door alle etappes
		for (int etappeTeller = 1; etappeTeller <= etappenummer; etappeTeller++) {
			//Laad de etappe uit de DB
			AbstractEtappeAndEindUitslag nextEtappe = tourFacade.getEtappeWithUitslag(etappeTeller);
			if (nextEtappe instanceof Etappe) {
				//Loop door alle Deelnemers
				for (Deelnemer nextDeelnemer: deelnemers) {
					double deelnemerBedragEtappe = 0;
					//Loop door renners
					Set<Renner> renners = nextDeelnemer.getRenners();
					for (Renner nextRenner: renners) {
						//Nu voor deze renner bepalen welke bedragen hij heeft gewonnen in deze etappe.
						//GeleTrui
						int positie = 0;
						positie = getPositieInUitslag(nextEtappe.getGeleTruiUitslag(), nextRenner);
						if (positie != 0) {
							deelnemerBedragEtappe = deelnemerBedragEtappe + etappeGeleTruiUitslagBedrag[positie - 1];
						}
						//GroeneTrui
						positie = getPositieInUitslag(nextEtappe.getGroeneTruiUitslag(), nextRenner);
						if (positie != 0) {
							deelnemerBedragEtappe = deelnemerBedragEtappe + etappeGroeneTruiUitslagBedrag[positie - 1];
						}
						//BolletjesTrui
						positie = getPositieInUitslag(nextEtappe.getBolletjesTruiUitslag(), nextRenner);
						if (positie != 0) {
							deelnemerBedragEtappe = deelnemerBedragEtappe + etappeGeleTruiUitslagBedrag[positie - 1];
						}
						//Als het een standaardetappe is dan ook nog bedragen voor etappe uitslag
						if (nextEtappe instanceof StandaardEtappe) {
							positie = getPositieInUitslag(((StandaardEtappe)nextEtappe).getEtappeUitslag(), nextRenner);
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
			//Zet nu het gewonnen bedrag uit de hashmap bij het bedrag
			deelnemerBedrag.setGewonnenBedrag(bedragenMap.get(nextDeelnemer.getNummer()));
			deelnemersMetBedragen.add(deelnemerBedrag);
			bedragenArrayTeller++;
		}
		
		return deelnemersMetBedragen;
	}
	
	private int getPositieInUitslag(Set<? extends Uitslag> uitslagen, Renner renner) {
		int positie = 0;
		boolean found = false;
		Iterator<? extends Uitslag> iterate = uitslagen.iterator();
		while(iterate.hasNext() && !found) {
			Uitslag nextUitslag = iterate.next();
			//TODO Dit moet eigenlijk met de equals method van Renner. Moeten we nog implementeren
			if (nextUitslag.getRenner().getNummer() == renner.getNummer()) {
				positie = nextUitslag.getPositie();
				found = true;
				//TODO Geoptimaliseerd  door deze entry uit deze Uitslagen Set te halen. De andere renners kunnen namelijk nooit op deze positie staan. Wel goed testen of dit werkt.
				uitslagen.remove(nextUitslag);
			}
		}
		return positie;
	}
	
	
	private double[] loadEtappeUitslagBedrag(List<UitslagBedrag> bedragen) {
		return setBedragen(tourConfig.getAantalEtappeUitslagen(), bedragen);
	}
	
	private double[] loadEtappeGeleTruiUitslagBedrag(List<UitslagBedrag> bedragen) {
		return setBedragen(tourConfig.getAantalEtappeGeleTruiUitslagen(), bedragen);
	}
	
	private double[] loadEtappeGroeneTruiUitslagBedrag(List<UitslagBedrag> bedragen) {
		return setBedragen(tourConfig.getAantalEtappeGroeneTruiUitslagen(), bedragen);
	}
	
	private double[] loadEtappeBolletjesTruiUitslagBedrag(List<UitslagBedrag> bedragen) {
		return setBedragen(tourConfig.getAantalEtappeBolletjesTruiUitslagen(), bedragen);
	}
	
	private double[] loadEindUitslagGeleTruiUitslagBedrag(List<UitslagBedrag> bedragen) {
		return setBedragen(tourConfig.getAantalEinduitslagGeleTruiUitslagen(), bedragen);
	}
	
	private double[] loadEindUitslagGroeneTruiUitslagBedrag(List<UitslagBedrag> bedragen) {
		return setBedragen(tourConfig.getAantalEinduitslagGroeneTruiUitslagen(), bedragen);
	}
	
	private double[] loadEindUitslagBolletjesTruiUitslagBedrag(List<UitslagBedrag> bedragen) {
		return setBedragen(tourConfig.getAantalEinduitslagBolletjesTruiUitslagen(), bedragen);
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
		
		double[] etappeBolletjesTruiUitslagBedrag = loadEtappeBolletjesTruiUitslagBedrag(uitslagBedragDao.loadAllUitslagBedragenPerCategorie(Categorien.BolletjesTrui));
		double[] eindUitslagGeleTruiUitslagBedrag = loadEindUitslagGeleTruiUitslagBedrag(uitslagBedragDao.loadAllUitslagBedragenPerCategorie(Categorien.GeleTruiEind));
		double[] eindUitslagGroeneTruiUitslagBedrag = loadEindUitslagGroeneTruiUitslagBedrag(uitslagBedragDao.loadAllUitslagBedragenPerCategorie(Categorien.GroeneTruiEind));
		double[] eindUitslagBolletjesTruiUitslagBedrag = loadEindUitslagBolletjesTruiUitslagBedrag(uitslagBedragDao.loadAllUitslagBedragenPerCategorie(Categorien.BolletjesTruiEind));
		
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
