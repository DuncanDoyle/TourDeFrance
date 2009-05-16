package nl.doyle.mccloud.tourdefrance.web.spring.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nl.doyle.mccloud.tourdefrance.dao.DeelnemerDao;
import nl.doyle.mccloud.tourdefrance.valueobjects.Deelnemer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/listDeelnemers.htm")
public class ListDeelnemersController {

	/**
	 * Commons-Logging logger.
	 */
	private static final Log LOG = LogFactory.getLog(ListDeelnemersController.class);

	/**
	 * The Dao for {@link Deelnemer} objects.
	 */
	private DeelnemerDao deelnemerDao;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView handleRequest() {
		// TODO GUI model definieren en mappings tussen GUI laag en DB / middle laag definieren in Dozer
		// create new model
		Map<String, List<Deelnemer>> deelnemersModel = new HashMap<String, List<Deelnemer>>();
		// Haal alle renners op uit de DB
		if (LOG.isDebugEnabled()) {
			LOG.debug("Putting Deelnemer objects in model map.");
		}
		deelnemersModel.put("deelnemers", deelnemerDao.loadAllDeelnemers());
		if (LOG.isDebugEnabled()) {
			LOG.debug("Returning ModelAnView 'listDeelnemers' with model 'model'");

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
	 * @param deelnemerDao
	 *            the deelnemerDao to set
	 */
	public void setDeelnemerDao(final DeelnemerDao deelnemerDao) {
		this.deelnemerDao = deelnemerDao;
	}

}
