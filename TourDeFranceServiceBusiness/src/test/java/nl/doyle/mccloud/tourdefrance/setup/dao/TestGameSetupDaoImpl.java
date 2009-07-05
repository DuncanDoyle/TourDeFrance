package nl.doyle.mccloud.tourdefrance.setup.dao;

import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

public class TestGameSetupDaoImpl extends AbstractDependencyInjectionSpringContextTests {
	
	GameSetupDao gameSetupDao;
	
	{
		setAutowireMode(AUTOWIRE_NO);
	}
	
	protected void onSetUp() throws Exception {
		gameSetupDao = (GameSetupDao) applicationContext.getBean("gameSetupDao");
		//insertTestData();
		
	}
	
	protected String[] getConfigLocations() {
		return new String[] { "classpath:spring/business_applicationcontext.xml", "classpath:spring/testBusiness_applicationcontext.xml" };
	}
	
	//TODO Goede test implementeren
	public void testOnzinTest() {
		assertEquals(true, true);
	}

}
