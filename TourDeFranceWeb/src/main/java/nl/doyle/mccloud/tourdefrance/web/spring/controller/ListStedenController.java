package nl.doyle.mccloud.tourdefrance.web.spring.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nl.doyle.mccloud.tourdefrance.dao.StadDao;
import nl.doyle.mccloud.tourdefrance.valueobjects.Stad;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/listSteden.htm")
public class ListStedenController {

	/**
	 * Commons-Logging logger.
	 */
	private static final Log LOG = LogFactory.getLog(ListStedenController.class);
	
	/**
	 * DAO for {@link Stad} objects.
	 */
	private StadDao stadDao;
	
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView handleRequest() {
		// TODO Deze code moet vervangen worden door iets dat niet afhankelijk is van de DAOs. Beetje ranzige code.
		List<Stad> steden = stadDao.loadAllSteden();
		Map<String, List<Stad>> stedenModel = new HashMap<String, List<Stad>>();
		if (LOG.isDebugEnabled()) {
			LOG.debug("Putting Stad objects in model map.");
		}
		stedenModel.put("steden", steden);
		if (LOG.isDebugEnabled()) {
			LOG.debug("Returning ModelAnView 'listSteden' with model 'model'");
			
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
	public void setStadDao(final StadDao stadDao) {
		this.stadDao = stadDao;
	}

	
	
}
