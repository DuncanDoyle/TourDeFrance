package nl.doyle.mccloud.tourdefrance.web.spring.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.doyle.mccloud.tourdefrance.dao.EindUitslagDao;
import nl.doyle.mccloud.tourdefrance.dao.PloegenTijdritDao;
import nl.doyle.mccloud.tourdefrance.dao.StandaardEtappeDao;
import nl.doyle.mccloud.tourdefrance.valueobjects.AbstractEtappeAndEindUitslag;
import nl.doyle.mccloud.tourdefrance.valueobjects.EindUitslag;
import nl.doyle.mccloud.tourdefrance.valueobjects.Etappe;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class ListEtappesController implements Controller {
	
	private static Log logger;
	private StandaardEtappeDao standaardEtappeDao;
	private PloegenTijdritDao ploegenTijdritDao;
	private EindUitslagDao eindUitslagDao;
	
	
	//Statically initialize logger
	{
		logger = LogFactory.getLog(ListEtappesController.class);
	}
	
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Deze code moet vervangen worden door iets dat niet afhankelijk is van de DAOs. Beetje ranzige code.
		List<Etappe> etappes = new ArrayList<Etappe>();
		etappes.addAll(standaardEtappeDao.loadAllStandaardEtappesWithStedenEager());
		etappes.addAll(ploegenTijdritDao.loadAllPloegenTijdrittenWithStedenEager());
		List<EindUitslag> eindUitslag = new ArrayList<EindUitslag>();
		eindUitslag.add(eindUitslagDao.loadEindUitslag());
		Map<String, List<? extends AbstractEtappeAndEindUitslag>> etappesModel = new HashMap<String, List<? extends AbstractEtappeAndEindUitslag>>();
		if (logger.isDebugEnabled()) {
			logger.debug("Putting Etappe objects in model map.");
		}
		etappesModel.put("etappes", etappes);
		etappesModel.put("einduitslag", eindUitslag);
		if (logger.isDebugEnabled()) {
			logger.debug("Returning ModelAnView 'listRenners' with model 'model'");
			
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
	public void setPloegenTijdritDao(PloegenTijdritDao ploegenTijdritDao) {
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
	public void setStandaardEtappeDao(StandaardEtappeDao standaardEtappeDao) {
		this.standaardEtappeDao = standaardEtappeDao;
	}



	public EindUitslagDao getEindUitslagDao() {
		return eindUitslagDao;
	}



	public void setEindUitslagDao(EindUitslagDao eindUitslagDao) {
		this.eindUitslagDao = eindUitslagDao;
	}
	
	
	
	
	

}
