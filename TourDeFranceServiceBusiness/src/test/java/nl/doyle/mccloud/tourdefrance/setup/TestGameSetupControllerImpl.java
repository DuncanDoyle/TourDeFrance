package nl.doyle.mccloud.tourdefrance.setup;

import nl.doyle.mccloud.tourdefrance.dao.DeelnemerDao;
import nl.doyle.mccloud.tourdefrance.dao.PloegenTijdritDao;
import nl.doyle.mccloud.tourdefrance.dao.RennerDao;
import nl.doyle.mccloud.tourdefrance.dao.StandaardEtappeDao;

import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

public class TestGameSetupControllerImpl extends AbstractDependencyInjectionSpringContextTests {

	GameSetupController setupController;
	DeelnemerDao deelnemerDao;
	RennerDao rennerDao;
	StandaardEtappeDao stdEtappeDao;
	PloegenTijdritDao ploegenTijdritDao;
	
	{
		setAutowireMode(AUTOWIRE_NO);
	}
	
	protected void onSetUp() throws Exception {
		setupController = (GameSetupController) applicationContext.getBean("gameSetupController");
		deelnemerDao = (DeelnemerDao) applicationContext.getBean("deelnemerDao");
		rennerDao = (RennerDao) applicationContext.getBean("rennerDao");
		stdEtappeDao = (StandaardEtappeDao) applicationContext.getBean("standaardEtappeDao");
		ploegenTijdritDao = (PloegenTijdritDao) applicationContext.getBean("ploegenTijdritDao");
	}
	
	protected String[] getConfigLocations() {
		return new String[] { "classpath:spring/business_applicationcontext.xml", "classpath:spring/testBusiness_applicationcontext.xml" };
	}
	
    /*
	private void testInitializeGame() {
		int aantalDeelnemers = 21;
		int aantalEtappes = 22;
		int ploegenTijdrit = 16;
		setupController.initializeGame(aantalDeelnemers, aantalEtappes, ploegenTijdrit);
	*/
		//Check of de geinitialiseerde waardes correct zijn
    /*
		assertEquals(deelnemerDao.loadAllDeelnemers().size(), aantalDeelnemers);
		Collection renners = rennerDao.loadAllRenners();
		assertEquals(renners.size(), (aantalDeelnemers * 9));
		Iterator itRenner = renners.iterator();
		while (itRenner.hasNext()) {
			//check dat geen enkele renner een rennernummer heeft dat % 10 = 0
			Renner currentRenner = (Renner) itRenner.next();
			assertTrue((currentRenner.getNummer() % 10) != 0);
		}
		assertEquals(stdEtappeDao.loadAllStandaardEtappes().size(), (aantalEtappes - 1));
		assertEquals(ploegenTijdritDao.loadAllPloegenTijdritten().size(), 1);
		assertTrue(!(ploegenTijdritDao.loadPloegenTijdrit(ploegenTijdrit) == null));
	}
	*/
	public void testGenerateDeelnemerTeams() {
		int aantalPloegen = 21;
		int aantalEtappes = 22;
		int ploegenTijdrit = 16;
		setupController.initializeGame(aantalPloegen, aantalEtappes, ploegenTijdrit);
		setupController.generateDeelnemerTeams();
		//TODO Goede unit test schrijven
		assertEquals(true, true);
	}

}
