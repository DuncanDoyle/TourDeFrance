package nl.doyle.mccloud.tourdefrance.web.spring.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.doyle.mccloud.tourdefrance.dao.StadDao;
import nl.doyle.mccloud.tourdefrance.valueobjects.Etappe;
import nl.doyle.mccloud.tourdefrance.valueobjects.Stad;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class ListStedenController implements Controller {

	private static Log logger;
	private StadDao stadDao;
	
	//Statically initialize logger
	{
		logger = LogFactory.getLog(ListStedenController.class);
	}
	
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Deze code moet vervangen worden door iets dat niet afhankelijk is van de DAOs. Beetje ranzige code.
		List<Stad> steden = stadDao.loadAllSteden();
		Map<String, List<Stad>> stedenModel = new HashMap<String, List<Stad>>();
		if (logger.isDebugEnabled()) {
			logger.debug("Putting Stad objects in model map.");
		}
		stedenModel.put("steden", steden);
		if (logger.isDebugEnabled()) {
			logger.debug("Returning ModelAnView 'listSteden' with model 'model'");
			
		}
		return new ModelAndView("listSteden", "model", stedenModel);
	}

	/**
	 * @return the stadDao
	 */
	public StadDao getStadDao() {
		return stadDao;
	}

	/**
	 * @param stadDao the stadDao to set
	 */
	public void setStadDao(StadDao stadDao) {
		this.stadDao = stadDao;
	}

	
	
}
