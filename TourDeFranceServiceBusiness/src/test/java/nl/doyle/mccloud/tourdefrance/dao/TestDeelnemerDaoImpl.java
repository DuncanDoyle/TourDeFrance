package nl.doyle.mccloud.tourdefrance.dao;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import nl.doyle.mccloud.tourdefrance.test.setup.SetupTestContoller;
import nl.doyle.mccloud.tourdefrance.valueobjects.Deelnemer;
import nl.doyle.mccloud.tourdefrance.valueobjects.Renner;

import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

public class TestDeelnemerDaoImpl extends AbstractDependencyInjectionSpringContextTests {

	//private ClassPathXmlApplicationContext context;
	private SetupTestContoller setupTestController;
	private DeelnemerDao dlnmrDao;
	
	{
		setAutowireMode(AUTOWIRE_NO);
	}
		
	protected void onSetUp() throws Exception {
		setupTestController.setupDatabase();
	}

	
	/**
	 * Injecteert de DeelnemerDao en SetupTestController in deze klasse.
	 * Dit is noodzakelijk omdat AUTOWIRING uit staat.
	 */
	protected void injectDependencies() {
		dlnmrDao = (DeelnemerDao) applicationContext.getBean("deelnemerDao");
		setupTestController = (SetupTestContoller) applicationContext.getBean("setupTestController");
	}

	@Override
	/**
	 * Geeft een String array met de lokaties van de Spring xml configuratiebestanden voor deze Unit Test terug
	 * 
	 * @return String[]: lokaties van de Spring configuratiebestanden.
	 */
	protected String[] getConfigLocations() {
		return new String[] { "classpath:spring/testBusiness_applicationcontext.xml" };
	}
	
	
	public void testLoadDeelnemer() {
		//Load deelnemer
		Deelnemer loadDeelnemer = dlnmrDao.loadDeelnemerEager(1003);
		//Deelnemer loadDeelnemer = dlnmrDao.loadDeelnemer(1003);
		Set<Renner> bla = loadDeelnemer.getRenners();
		Iterator<Renner> it = bla.iterator();
		while (it.hasNext()) {
			Renner bla2  = it.next();
			
		}
		assertEquals(loadDeelnemer.getNummer(), 1003);
	}
	
	/*
	 * Test method for 'nl.doyle.mccloud.tourdefrance.dao.hibernate.DeelnemerDaoHibernateImpl.loadAllDeelnemers()
	 */
    
	public void testLoadAllDeelnemers() {
		Collection<Deelnemer> deelnemers = dlnmrDao.loadAllDeelnemers();
		Iterator iterate = deelnemers.iterator();
		while (iterate.hasNext()) {
			Deelnemer dlnmr = (Deelnemer) iterate.next();
			System.out.println(dlnmr.getNummer());
			System.out.println(dlnmr.getVoornaam());
			System.out.println(dlnmr.getAchternaam());
			
		}
		
		//TODO Assertion of alle deelnemers ook daadwerkelijk geladen zijn
		assertEquals(21, deelnemers.size());
		
	}
	
	
	/*
	public void testDeleteDeelnemer() {
		
		Deelnemer deelnemer = dlnmrDao.loadDeelnemer(1003);
		System.out.println(deelnemer.getAchternaam());
		dlnmrDao.deleteDeelnemer(deelnemer);
		
	}
	*/
	/*
	public void testDeleteAllDeelnemers() {
		//Verwijder nu alle deelnemer data en alle Deelnemer_Renner data
		//dlnmrDao.deleteAllDeelnemers();
	}
	*/
}
