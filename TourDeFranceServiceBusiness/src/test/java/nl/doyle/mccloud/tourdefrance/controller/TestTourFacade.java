package nl.doyle.mccloud.tourdefrance.controller;

import java.util.Iterator;
import java.util.List;

import nl.doyle.mccloud.tourdefrance.dto.DeelnemerWithRennersDto;
import nl.doyle.mccloud.tourdefrance.test.setup.SetupTestContoller;

import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

public class TestTourFacade extends
		AbstractDependencyInjectionSpringContextTests {

	
	private SetupTestContoller setupTestController;
	private TourFacadeImpl tourFacade;
	
	
	{
		setAutowireMode(AUTOWIRE_NO);
	}
	
	
	
	protected void onSetUp() throws Exception {
		setupTestController.setupDatabase();
	}
	
	public void testGetAllDeelnemersAndRenners() {
		List<DeelnemerWithRennersDto> deelnemersAndRenners = tourFacade.getAllDeelnemersAndRenners();
		
		Iterator<DeelnemerWithRennersDto> iterate = deelnemersAndRenners.iterator();
		assertEquals(true, iterate.hasNext());
		while(iterate.hasNext()) {
			System.out.println(iterate.next().toString());
		}
	}

	/**
	 * Injecteert de DeelnemerDao en SetupTestController in deze klasse.
	 * Dit is noodzakelijk omdat AUTOWIRING uit staat.
	 */
	protected void injectDependencies() {
		tourFacade = (TourFacadeImpl) applicationContext.getBean("tourFacade");
		setupTestController = (SetupTestContoller) applicationContext.getBean("setupTestController");
	}
	
	
	
	protected String[] getConfigLocations() {
		return new String[] { "classpath:spring/business_applicationcontext.xml", "classpath:spring/testBusiness_applicationcontext.xml" };
	}

	/**
	 * @return the setupTestController
	 */
	public SetupTestContoller getSetupTestController() {
		return setupTestController;
	}

	/**
	 * @param setupTestController the setupTestController to set
	 */
	public void setSetupTestController(SetupTestContoller setupTestController) {
		this.setupTestController = setupTestController;
	}

	/**
	 * @return the tourFacade
	 */
	public TourFacadeImpl getTourFacade() {
		return tourFacade;
	}

	/**
	 * @param tourFacade the tourFacade to set
	 */
	public void setTourFacade(TourFacadeImpl tourFacade) {
		this.tourFacade = tourFacade;
	}
	
	
	
	
	

}
