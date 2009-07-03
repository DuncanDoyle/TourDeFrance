package nl.doyle.mccloud.tourdefrance.web.spring.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nl.doyle.mccloud.tourdefrance.dao.RennerDao;
import nl.doyle.mccloud.tourdefrance.valueobjects.Renner;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Spring MVC Controller which retrieves a list of all {@link Renner}s and stores them in the model to be used in the view.
 * 
 * @author Duncan Doyle
 * @since 0,1
 */
@Controller
@RequestMapping("/listRenners.htm")
public class ListRennersController {

	/**
	 * Commons-Logging logger.
	 */
	private static final Log LOG = LogFactory.getLog(ListRennersController.class);

	/**
	 * The DAO for {@link Renner} objects.
	 */
	private RennerDao rennerDao;

	/**
	 * Handelt het request van ListRenners af. Deze methode haalt (indirect) de lijst met alle Renners uit de DB en stopt deze in een model
	 * (d.m.v. een Hashmap). Vervolgens wordt dit model met de viewnaam <i>listRenners</i> aan de Spring DispatcherServlet teruggegeven.
	 * 
	 * @return ModelAndView the model and view object containing the populated model and viewname
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView handleRequest() {
		// TODO GUI model definieren en mappings tussen GUI laag en DB / middle laag definieren in Dozer

		// create new model
		Map<String, List<Renner>> rennersModel = new HashMap<String, List<Renner>>();
		// retrieve racers from DB
		if (LOG.isDebugEnabled()) {
			LOG.debug("Putting Renner objects in model map.");
		}
		rennersModel.put("renners", rennerDao.loadAllRenners());
		if (LOG.isDebugEnabled()) {
			LOG.debug("Returning ModelAnView 'listRenners' with model 'model'");

		}
		return new ModelAndView("listRenners", "model", rennersModel);
	}

	/**
	 * @return the rennerDao
	 */
	public RennerDao getRennerDao() {
		return rennerDao;
	}

	/**
	 * @param rennerDao
	 *            the rennerDao
	 */
	public void setRennerDao(final RennerDao rennerDao) {
		this.rennerDao = rennerDao;
	}

}
