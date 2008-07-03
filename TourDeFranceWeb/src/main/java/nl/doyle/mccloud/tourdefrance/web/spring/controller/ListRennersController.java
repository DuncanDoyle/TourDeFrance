package nl.doyle.mccloud.tourdefrance.web.spring.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.doyle.mccloud.tourdefrance.dao.RennerDao;
import nl.doyle.mccloud.tourdefrance.valueobjects.Renner;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 * @author mccloud 
 * 
 * Controller klasse om een lijst met alle Renners op te halen uit de DB en
 * terug te geven in een Spring ModelAndView aan de DispatcherServlet.
 */
public class ListRennersController extends AbstractController {

	private static Log logger;
	private RennerDao rennerDao;
	
	
	//Statically initialize logger
	{
		logger = LogFactory.getLog(ListRennersController.class);
	}
	
	
	/**
	 * Handelt het request van ListRenners  af. Deze methode haalt (indirect) de lijst
	 * met alle Renners uit de DB en stopt deze in een model (d.m.v. een Hashmap).
	 * Vervolgens wordt dit model met de viewnaam <i>listRenners</i> aan de
	 * Spring DispatcherServlet teruggegeven.
	 *
	 * @param arg0: HttpServletRequest
	 * @param arg1: HttpServletResponse
	 * 
	 * @return ModelAndView
	 */
	public ModelAndView handleRequestInternal(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		//TODO GUI model definieren en mappings tussen GUI laag en DB / middle laag definieren in Dozer
		
		//create new model
		Map<String, List<Renner>> rennersModel = new HashMap<String, List<Renner>>();
		//Haal alle renners op uit de DB
		if (logger.isDebugEnabled()) {
			logger.debug("Putting Renner objects in model map.");
		}
		rennersModel.put("renners", rennerDao.loadAllRenners());
		if (logger.isDebugEnabled()) {
			logger.debug("Returning ModelAnView 'listRenners' with model 'model'");
			
		}
		return new ModelAndView("listRenners2", "model", rennersModel);
	}
	
	
	
	
	
	/**
	 * Getter voor de Renners Dao
	 * 
	 * @return RennerDao
	 */
	public RennerDao getRennerDao() {
		return rennerDao;
	}

	/**
	 * Setter voor de Renner Dao
	 * 
	 * @param rennerDao
	 */
	public void setRennerDao(RennerDao rennerDao) {
		this.rennerDao = rennerDao;
	}
	
	

	
	
	
	
	
}
