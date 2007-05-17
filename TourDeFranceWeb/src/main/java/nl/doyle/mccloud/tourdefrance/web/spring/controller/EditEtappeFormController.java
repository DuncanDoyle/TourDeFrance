package nl.doyle.mccloud.tourdefrance.web.spring.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import nl.doyle.mccloud.tourdefrance.dao.PloegenTijdritDao;
import nl.doyle.mccloud.tourdefrance.dao.StadDao;
import nl.doyle.mccloud.tourdefrance.dao.StandaardEtappeDao;
import nl.doyle.mccloud.tourdefrance.valueobjects.Etappe;
import nl.doyle.mccloud.tourdefrance.web.spring.command.EtappeCommand;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

public class EditEtappeFormController extends SimpleFormController {
	
	private static final Log logger = LogFactory.getLog(EditEtappeFormController.class);
	private StandaardEtappeDao standaardEtappeDao;
	private PloegenTijdritDao ploegenTijdritDao;
	private StadDao stadDao;
	
	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.validation.BindException)
	 */
	@Override
	protected ModelAndView onSubmit(Object command) throws ServletException {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) {
			logger.debug("Saving etappe!!");
		}
		
		return new ModelAndView(new RedirectView(getSuccessView()));
	}

	
	
	@Override
	/**
	 * Override de default binding van form entry en command controller zodat de datum van een string naar date geconverteerd kan worden.
	 */
	protected void onBind(HttpServletRequest arg0, Object arg1) throws Exception {
		//TODO dit klopt niet. Zie Spring MVC binding bookmark in Firefox. 
		
		super.onBind(arg0, arg1);
		logger.debug("Starting to bind values!");
		EtappeCommand command = (EtappeCommand) arg1;
		command.setEtappenummer(Integer.parseInt((String) arg0.getParameter("etappenummer")));
		logger.debug("Setting etappeNummer: " + command.getEtappenummer());
		command.setFinishPlaatsIndex(Integer.parseInt((String) arg0.getParameter("finishStedenCombo")));
		logger.debug("Setting finishplaatsIndex: " + command.getFinishPlaatsIndex());
		command.setStartPlaatsIndex(Integer.parseInt((String) arg0.getParameter("startStedenCombo")));
		logger.debug("Setting startplaatsIndex: " + command.getStartPlaatsIndex());
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		command.setDatum(formatter.parse((String) arg0.getParameter("datum")));
		logger.debug("Setting datum: " + command.getDatum());
	}
	

	
	protected Object formBackingObject(HttpServletRequest request) throws ServletException, EditEtappeFormRequestException {
    	//TODO Dit moet goed geimplementeerd worden zodat er geen verkeerde waardes ingevuld kunnen worden. Security
    	Integer etappeNummer = ServletRequestUtils.getIntParameter(request,"etappe");
    	//check of deze parameter wel is ingevuld. Zoniet dan is 'nummer' 'null'.
    	int nummer;
    	if (etappeNummer != null) {
    		nummer  = etappeNummer;
    	} else {
    		throw new EditEtappeFormRequestException("Etappenummer niet opgegeven.");
    	}
    	logger.debug("Etappenummer = " + nummer);
    
    	//Haal de renner op uit de database
    	EtappeCommand etappe = new EtappeCommand();
    	//TODO Dit moet anders, we moeten kijken of de etappe een standaardetappe is, anders Ploegentijdrit laden.
    	Etappe dbEtappe = standaardEtappeDao.loadStandaardEtappeWithStartAndFinish(nummer);
    	if (dbEtappe == null) {
    		dbEtappe = ploegenTijdritDao.loadPloegenTijdritEager(nummer);
    		etappe.setPloegenTijdrit(true);
    	} else {
    		etappe.setPloegenTijdrit(false);
    	}
    	if (dbEtappe != null) {
    		etappe.setDatum(dbEtappe.getDatum());
    		etappe.setEtappenummer(dbEtappe.getEtappenummer());
    		etappe.setSteden(stadDao.loadAllSteden());
    		//Bepaal nu index in de lijst van de startplaats van deze etappe
    		etappe.setStartPlaatsIndex(dbEtappe.getStartplaats().getId());
    		etappe.setFinishPlaatsIndex(dbEtappe.getFinishplaats().getId());
    	} else {
    		throw new EditEtappeFormRequestException("Etappe niet gevonden in database.");
    	}
    	logger.debug("FormBackingObject geinstantieerd.");
    	return etappe;
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
