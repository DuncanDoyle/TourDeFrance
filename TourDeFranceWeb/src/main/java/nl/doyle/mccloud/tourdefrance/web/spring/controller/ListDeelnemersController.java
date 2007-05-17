package nl.doyle.mccloud.tourdefrance.web.spring.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.doyle.mccloud.tourdefrance.dao.DeelnemerDao;
import nl.doyle.mccloud.tourdefrance.valueobjects.Deelnemer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

public class ListDeelnemersController extends AbstractController {
	
	private static Log logger;
	private DeelnemerDao deelnemerDao;
	
	
	//Statically initialize logger
	{
		logger = LogFactory.getLog(ListDeelnemersController.class);
	}
	
	public ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//TODO GUI model definieren en mappings tussen GUI laag en DB / middle laag definieren in Dozer
		//create new model
		Map<String, List<Deelnemer>> deelnemersModel = new HashMap<String, List<Deelnemer>>();
		//Haal alle renners op uit de DB
		if (logger.isDebugEnabled()) {
			logger.debug("Putting Deelnemer objects in model map.");
		}
		deelnemersModel.put("deelnemers", deelnemerDao.loadAllDeelnemers());
		if (logger.isDebugEnabled()) {
			logger.debug("Returning ModelAnView 'listDeelnemers' with model 'model'");
			
		}
		return new ModelAndView("listDeelnemers", "model", deelnemersModel);
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
	
	
	
	
	

}
