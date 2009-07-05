package nl.doyle.mccloud.cactus;
import junit.framework.TestCase;

import nl.doyle.mccloud.tourdefrance.dao.DeelnemerDao;

import org.springframework.beans.factory.access.BeanFactoryLocator;
import org.springframework.beans.factory.access.BeanFactoryReference;
import org.springframework.beans.factory.access.SingletonBeanFactoryLocator;


public class TestDeelnemers extends TestCase {

	private DeelnemerDao deelnemerDao;
	
	@Override
	protected void setUp() throws Exception {
		//Laad Spring context (WebApplicationContext die automatisch geladen wordt door de ContextLoaderListener)
		//WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(sc);
		BeanFactoryLocator bfl = SingletonBeanFactoryLocator.getInstance("beanRefContext.xml");
		BeanFactoryReference bfr = bfl.useBeanFactory("businessContext");
		deelnemerDao = (DeelnemerDao) bfr.getFactory().getBean("deelnemerDao");
		super.setUp();
	}
	
	public void testLoadAllDeelnemers() {
		//TODO Unit Test beter implementeren. Slaat nu nergens op.
		deelnemerDao.loadAllDeelnemers();
		assertEquals(true, true);
	}
	
	
	
	
	
}
