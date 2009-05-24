package nl.doyle.mccloud.tourdefrance.web.spring.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nl.doyle.mccloud.tourdefrance.dao.EindUitslagDao;
import nl.doyle.mccloud.tourdefrance.dao.PloegenTijdritDao;
import nl.doyle.mccloud.tourdefrance.dao.StandaardEtappeDao;
import nl.doyle.mccloud.tourdefrance.valueobjects.AbstractEtappeAndEindUitslag;
import nl.doyle.mccloud.tourdefrance.valueobjects.EindUitslag;
import nl.doyle.mccloud.tourdefrance.valueobjects.Etappe;
import nl.doyle.mccloud.tourdefrance.valueobjects.PloegenTijdrit;
import nl.doyle.mccloud.tourdefrance.valueobjects.StandaardEtappe;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/listEtappes.htm")
public class ListEtappesController {
	
	/**
	 * Commons-Logging logger.
	 */
	private static Log LOG = LogFactory.getLog(ListEtappesController.class);
	
	/**
	 * DAO for {@link StandaardEtappe} objects.
	 */
	private StandaardEtappeDao standaardEtappeDao;
	
	/**
	 * DAO for {@link PloegenTijdrit} objects.
	 */
	private PloegenTijdritDao ploegenTijdritDao;
	
	/**
	 * DAO for {@link EindUitslag} objects.
	 */
	private EindUitslagDao eindUitslagDao;

	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView handleRequest() {
		// TODO Deze code moet vervangen worden door iets dat niet afhankelijk is van de DAOs. Beetje ranzige code.
		List<Etappe> etappes = new ArrayList<Etappe>();
		etappes.addAll(standaardEtappeDao.loadAllStandaardEtappesWithStartAndFinishEager());
		etappes.addAll(ploegenTijdritDao.loadAllPloegenTijdrittenWithStartAndFinishEager());
		List<EindUitslag> eindUitslag = new ArrayList<EindUitslag>();
		eindUitslag.add(eindUitslagDao.loadEindUitslag());
		Map<String, List<? extends AbstractEtappeAndEindUitslag>> etappesModel = new HashMap<String, List<? extends AbstractEtappeAndEindUitslag>>();
		if (LOG.isDebugEnabled()) {
			LOG.debug("Putting Etappe objects in model map.");
		}
		etappesModel.put("etappes", etappes);
		etappesModel.put("einduitslag", eindUitslag);
		if (LOG.isDebugEnabled()) {
			LOG.debug("Returning ModelAnView 'listRenners' with model 'model'");
			
		}
		return new ModelAndView("listEtappes", "model", etappesModel);
	}

	
	
	/**
	 * @return the ploegenTijdritDao
	 */
	public PloegenTijdritDao getPloegenTijdritDao() {
		return ploegenTijdritDao;
	}

	/**
	 * @param ploegenTijdritDao the ploegenTijdritDao to set
	 */
	public void setPloegenTijdritDao(final PloegenTijdritDao ploegenTijdritDao) {
		this.ploegenTijdritDao = ploegenTijdritDao;
	}

	/**
	 * @return the standaardEtappeDao
	 */
	public StandaardEtappeDao getStandaardEtappeDao() {
		return standaardEtappeDao;
	}

	/**
	 * @param standaardEtappeDao the standaardEtappeDao to set
	 */
	public void setStandaardEtappeDao(final StandaardEtappeDao standaardEtappeDao) {
		this.standaardEtappeDao = standaardEtappeDao;
	}



	public EindUitslagDao getEindUitslagDao() {
		return eindUitslagDao;
	}



	public void setEindUitslagDao(final EindUitslagDao eindUitslagDao) {
		this.eindUitslagDao = eindUitslagDao;
	}
	
	
	
	
	

}
