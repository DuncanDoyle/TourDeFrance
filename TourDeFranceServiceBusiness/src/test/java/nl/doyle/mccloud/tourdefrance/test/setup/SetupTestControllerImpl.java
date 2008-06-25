package nl.doyle.mccloud.tourdefrance.test.setup;

import java.util.GregorianCalendar;
import java.util.HashSet;

import nl.doyle.mccloud.tourdefrance.dao.BolletjesTruiUitslagDao;
import nl.doyle.mccloud.tourdefrance.dao.DeelnemerDao;
import nl.doyle.mccloud.tourdefrance.dao.EtappeUitslagDao;
import nl.doyle.mccloud.tourdefrance.dao.GeleTruiUitslagDao;
import nl.doyle.mccloud.tourdefrance.dao.GroeneTruiUitslagDao;
import nl.doyle.mccloud.tourdefrance.dao.PloegenTijdritDao;
import nl.doyle.mccloud.tourdefrance.dao.RennerDao;
import nl.doyle.mccloud.tourdefrance.dao.StandaardEtappeDao;
import nl.doyle.mccloud.tourdefrance.dao.TeamDao;
import nl.doyle.mccloud.tourdefrance.setup.dao.GameSetupDao;
import nl.doyle.mccloud.tourdefrance.valueobjects.BolletjesTruiUitslag;
import nl.doyle.mccloud.tourdefrance.valueobjects.Deelnemer;
import nl.doyle.mccloud.tourdefrance.valueobjects.Etappe;
import nl.doyle.mccloud.tourdefrance.valueobjects.EtappeUitslag;
import nl.doyle.mccloud.tourdefrance.valueobjects.GeleTruiUitslag;
import nl.doyle.mccloud.tourdefrance.valueobjects.GroeneTruiUitslag;
import nl.doyle.mccloud.tourdefrance.valueobjects.PloegenTijdrit;
import nl.doyle.mccloud.tourdefrance.valueobjects.Renner;
import nl.doyle.mccloud.tourdefrance.valueobjects.Stad;
import nl.doyle.mccloud.tourdefrance.valueobjects.StandaardEtappe;
import nl.doyle.mccloud.tourdefrance.valueobjects.Team;

import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * Class which inserts test data into the test database. Unit tests use this class to setup the DB. 
 * 
 * @author Duncan Doyle
 * @since 0.1
 */

public class SetupTestControllerImpl implements SetupTestContoller {

	
	
	private ClassPathXmlApplicationContext context;
	private DeelnemerDao deelnemerDao;
	private BolletjesTruiUitslagDao bolletjesTruiUistlagDao;
	private EtappeUitslagDao etappeUitslagDao;
	private GameSetupDao gameSetupDao;
	private GeleTruiUitslagDao geleTruiUitslagDao;
	private GroeneTruiUitslagDao groeneTruiUistlagDao;
	private PloegenTijdritDao ploegenTijdritDao;
	private RennerDao rennerDao;
	private StandaardEtappeDao standaardEtappeDao;
	private TeamDao teamDao;
	
	public SetupTestControllerImpl() {
		
		
	}
	
	
	public void setupDatabase() {
		gameSetupDao.deleteAllRecordsFromDatabase();
		insertTestData();
	}
	
	private void insertTestData() {
		
		//maak alle 21 deelnemers aan
		for (int teller =  0; teller < 21; teller++) {
			Deelnemer test = new Deelnemer();
			test.setAchternaam("de Tester" + teller);
			test.setEmail("Jan.de.Tester" + teller + "@testerdetest.com");
			test.setNummer(teller + 1001);
			test.setRekeningnummer("1234567" + teller);
			test.setVoornaam("Jan" + teller);
			//test.setRenners(renners);
			HashSet<Renner> renners = new HashSet<Renner>();
			//Maak de eerste negen renners aan
			for (int rennerTeller=0; rennerTeller < 9; rennerTeller++) {
				Renner testRenner = new Renner();
				testRenner.setAchternaam("Boogerd" + teller + (rennerTeller + 1));
				testRenner.setNummer((teller * 10) + (rennerTeller + 1));
				testRenner.setVoornaam("Michael" + teller + (rennerTeller + 1));
				renners.add(testRenner);
			}
			test.setRenners(renners);
			deelnemerDao.saveDeelnemer(test);
		}
		//alle deelnemers en renners aangemaakt. Dan nu 21 etappes waarvan 1 ploegentijdrit in etappe 15
		
		for (int teller = 0; teller < 21; teller++) {
			Etappe testEtappe = null;
			if (teller != 14) {
				testEtappe = new StandaardEtappe();
			} else {
				testEtappe = new PloegenTijdrit();
			}
			
			GregorianCalendar calendar = (GregorianCalendar) GregorianCalendar.getInstance();
			testEtappe.setDatum(calendar.getTime());
			testEtappe.setEtappenummer(teller + 1);
			Stad start = new Stad();
			Stad finish = new Stad();
			start.setLand("Nederland");
			start.setStad("Capelle aan den IJssel");
			start.setLatitude(4.5);
			start.setLongitude(5.5);
			finish.setLand("Nederland");
			finish.setStad("Rotterdam");
			finish.setLatitude(4.5);
			finish.setLongitude(5.5);
			testEtappe.setStartplaats(start);
			testEtappe.setFinishplaats(finish);
			//maak nu de uitslagen
			//GeleTruiUitslag
			HashSet<GeleTruiUitslag> geleTrui = new HashSet<GeleTruiUitslag>();
			HashSet<GroeneTruiUitslag> groeneTrui = new HashSet<GroeneTruiUitslag>();
			HashSet<BolletjesTruiUitslag> bolletjesTrui = new HashSet<BolletjesTruiUitslag>();
			HashSet<EtappeUitslag> etappeUitslag = new HashSet<EtappeUitslag>();
			
		
	
			for(int truiTeller=0; truiTeller < 3; truiTeller++) {
				
				GeleTruiUitslag geleUitslag = new GeleTruiUitslag();
				geleUitslag.setEtappenummer(teller + 1);
				geleUitslag.setPositie(truiTeller + 1);
				geleUitslag.setRenner(rennerDao.loadRenner(truiTeller + 1));
				geleTrui.add(geleUitslag);
				
				//GroeneTruiUitslag
				GroeneTruiUitslag groeneUitslag = new GroeneTruiUitslag();
				groeneUitslag.setEtappenummer(teller + 1);
				groeneUitslag.setPositie(truiTeller + 1);
				groeneUitslag.setRenner(rennerDao.loadRenner(truiTeller + 1));
				groeneTrui.add(groeneUitslag);
				
				//BolletjesTruiUitslag
				BolletjesTruiUitslag bolletjesUitslag = new BolletjesTruiUitslag();
				bolletjesUitslag.setEtappenummer(teller + 1);
				bolletjesUitslag.setPositie(truiTeller + 1);
				bolletjesUitslag.setRenner(rennerDao.loadRenner(truiTeller +1));
				bolletjesTrui.add(bolletjesUitslag);
				
			}
			//FF een testjes
			
			testEtappe.setGeleTruiUitslag(geleTrui);
			testEtappe.setGroeneTruiUitslag(groeneTrui);
			testEtappe.setBolletjesTruiUitslag(bolletjesTrui);
			
			if (testEtappe instanceof StandaardEtappe){
				for (int truiTeller=0; truiTeller < 15 ; truiTeller++) {
					EtappeUitslag testEtappeUitslag = new EtappeUitslag();
					testEtappeUitslag.setEtappenummer(teller + 1);
					testEtappeUitslag.setPositie(truiTeller + 1);
					if (truiTeller >= 9) {
						testEtappeUitslag.setRenner(rennerDao.loadRenner(truiTeller + 2));
					} else {
						testEtappeUitslag.setRenner(rennerDao.loadRenner(truiTeller + 1));
					}
					
					etappeUitslag.add(testEtappeUitslag);
				}
				((StandaardEtappe) testEtappe).setEtappeUitslag(etappeUitslag);
				standaardEtappeDao.saveStandaardEtappe((StandaardEtappe)testEtappe);
				
			} else {
				ploegenTijdritDao.savePloegenTijdrit((PloegenTijdrit)testEtappe);
			}
		}
		//Nu de teams aanmaken
		for (int teamTeller=0; teamTeller< 21; teamTeller++) {
			Team team = new Team();
			team.setNaam("TestTeam" + (teamTeller + 1));
			team.setNummer(teamTeller + 1);
			HashSet<Renner> renners = new HashSet<Renner>();
			for (int rennerTeller = 0; rennerTeller < 9; rennerTeller++) {
				Renner testRenner = rennerDao.loadRenner((teamTeller * 10) + (rennerTeller +1));
				renners.add(testRenner);
			}
			team.setRenners(renners);
			teamDao.saveTeam(team);
		}
		
	}
	

	public BolletjesTruiUitslagDao getBolletjesTruiUistlagDao() {
		return bolletjesTruiUistlagDao;
	}

	public void setBolletjesTruiUistlagDao(
			BolletjesTruiUitslagDao bolletjesTruiUistlagDao) {
		this.bolletjesTruiUistlagDao = bolletjesTruiUistlagDao;
	}

	public ClassPathXmlApplicationContext getContext() {
		return context;
	}

	public void setContext(ClassPathXmlApplicationContext context) {
		this.context = context;
	}

	public DeelnemerDao getDeelnemerDao() {
		return deelnemerDao;
	}

	public void setDeelnemerDao(DeelnemerDao deelnemerDao) {
		this.deelnemerDao = deelnemerDao;
	}

	public EtappeUitslagDao getEtappeUitslagDao() {
		return etappeUitslagDao;
	}

	public void setEtappeUitslagDao(EtappeUitslagDao etappeUitslagDao) {
		this.etappeUitslagDao = etappeUitslagDao;
	}

	public GameSetupDao getGameSetupDao() {
		return gameSetupDao;
	}

	public void setGameSetupDao(GameSetupDao gameSetupDao) {
		this.gameSetupDao = gameSetupDao;
	}

	public GeleTruiUitslagDao getGeleTruiUitslagDao() {
		return geleTruiUitslagDao;
	}

	public void setGeleTruiUitslagDao(GeleTruiUitslagDao geleTruiUitslagDao) {
		this.geleTruiUitslagDao = geleTruiUitslagDao;
	}

	public GroeneTruiUitslagDao getGroeneTruiUistlagDao() {
		return groeneTruiUistlagDao;
	}

	public void setGroeneTruiUistlagDao(GroeneTruiUitslagDao groeneTruiUistlagDao) {
		this.groeneTruiUistlagDao = groeneTruiUistlagDao;
	}

	public PloegenTijdritDao getPloegenTijdritDao() {
		return ploegenTijdritDao;
	}

	public void setPloegenTijdritDao(PloegenTijdritDao ploegenTijdritDao) {
		this.ploegenTijdritDao = ploegenTijdritDao;
	}

	public RennerDao getRennerDao() {
		return rennerDao;
	}

	public void setRennerDao(RennerDao rennerDao) {
		this.rennerDao = rennerDao;
	}

	public StandaardEtappeDao getStandaardEtappeDao() {
		return standaardEtappeDao;
	}

	public void setStandaardEtappeDao(StandaardEtappeDao standaardEtappeDao) {
		this.standaardEtappeDao = standaardEtappeDao;
	}

	public TeamDao getTeamDao() {
		return teamDao;
	}

	public void setTeamDao(TeamDao teamDao) {
		this.teamDao = teamDao;
	}
	
	
	
}
