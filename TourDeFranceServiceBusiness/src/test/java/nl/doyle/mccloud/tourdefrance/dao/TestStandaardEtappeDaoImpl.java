package nl.doyle.mccloud.tourdefrance.dao;

import java.util.List;

import nl.doyle.mccloud.tourdefrance.test.setup.SetupTestContoller;
import nl.doyle.mccloud.tourdefrance.valueobjects.GeleTruiUitslag;
import nl.doyle.mccloud.tourdefrance.valueobjects.StandaardEtappe;

import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

public class TestStandaardEtappeDaoImpl extends AbstractDependencyInjectionSpringContextTests {

	
	StandaardEtappeDao standaardEtappeDao;
	SetupTestContoller setupTestController;
	RennerDao rennerDao;
	
	{
		setAutowireMode(AUTOWIRE_NO);
	}
	
	protected void onSetUp() throws Exception {
		standaardEtappeDao = (StandaardEtappeDao) applicationContext.getBean("standaardEtappeDao");
		rennerDao = (RennerDao) applicationContext.getBean("rennerDao");
		setupTestController = (SetupTestContoller) applicationContext.getBean("setupTestController");
		//setupTestController.setupDatabase();
	}
	
	protected String[] getConfigLocations() {
		return new String[] { "classpath:spring/business_applicationcontext.xml", "classpath:spring/testBusiness_applicationcontext.xml" };
	}
	
	public void testLoadAllStandaardEtappes() {
	
		List<StandaardEtappe> etappes = standaardEtappeDao.loadAllStandaardEtappes();
		assertEquals(20, etappes.size());
	
	}
		
	public void testLoadStandaardEtappeWithUitslagEager() {
		StandaardEtappe etappe = standaardEtappeDao.loadStandaardEtappeWithUitslagEager(1);
		System.out.println("Etappenummer: " + etappe.getEtappenummer());
		System.out.println("Datum: " + etappe.getDatum());
		System.out.println("Startplaats: " + etappe.getStartplaats());
		System.out.println("Finishplaats: " + etappe.getFinishplaats());
		for (GeleTruiUitslag nextUitslag: etappe.getGeleTruiUitslag() ) {
			System.out.println("Nummer: " + nextUitslag.getRenner().getNummer());
			System.out.println("Voornaam: " + nextUitslag.getRenner().getVoornaam());
			System.out.println("Achternaam: " + nextUitslag.getRenner().getAchternaam());
		}
	}
		
	/*
	public void testLoadStandaardEtappe() {
		fail("Not yet implemented");
	}

	public void testSaveStandaardEtappe() {
		fail("Not yet implemented");
	}

	public void testSetStandaardEtappeDaoHibernate() {
		fail("Not yet implemented");
	}

	public void testSetStandaardEtappeDaoJdbc() {
		fail("Not yet implemented");
	}
*/
}
