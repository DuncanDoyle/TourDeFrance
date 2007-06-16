package nl.doyle.mccloud.tourdefrance.web.spring.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import nl.doyle.mccloud.tourdefrance.controller.TourFacade;
import nl.doyle.mccloud.tourdefrance.dao.PloegenTijdritDao;
import nl.doyle.mccloud.tourdefrance.dao.StadDao;
import nl.doyle.mccloud.tourdefrance.dao.StandaardEtappeDao;
import nl.doyle.mccloud.tourdefrance.valueobjects.Etappe;
import nl.doyle.mccloud.tourdefrance.valueobjects.GeleTruiUitslag;
import nl.doyle.mccloud.tourdefrance.valueobjects.PloegenTijdrit;
import nl.doyle.mccloud.tourdefrance.valueobjects.Stad;
import nl.doyle.mccloud.tourdefrance.valueobjects.StandaardEtappe;
import nl.doyle.mccloud.tourdefrance.web.spring.command.EtappeCommand;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

public class EditEtappeFormController extends SimpleFormController {
	
	private static final Log logger = LogFactory.getLog(EditEtappeFormController.class);
	private StandaardEtappeDao standaardEtappeDao;
	private PloegenTijdritDao ploegenTijdritDao;
	private StadDao stadDao;
	private Etappe dbEtappe;
	private TourFacade tourFacade;
	
	
	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.validation.BindException)
	 */
	@Override
	protected ModelAndView onSubmit(Object command) throws ServletException {
		//Deze volledige code gaat met updaten goed omdat de Uitslagen sets niet geinitialiseerd zijn door Hibernate en daardoor tijdens de update waarschijnlijk ook niet worden aangepast.
		//In het deelnemer scherm ging dit fout omdat we daar het scherm volledig los hebben gekoppeld van Hibernate en bij het opslaan een nieuw object aanmaken.
		//Hierdoor gingen bij de Deelnemer de referenties naar de verschillende renners verloren.
		if (logger.isDebugEnabled()) {
			logger.debug("Saving etappe!!");
		}
		if (dbEtappe instanceof Etappe) {
			//TODO Validator implementeren
			//Alle validatie is al gedaan door de validator. Nu de etappe opslaan
			EtappeCommand etappe = (EtappeCommand) command;
			//Eerst verschillen bepalen tussen object uit DB en ingevulde waardes
			dbEtappe.setOmschrijving(etappe.getOmschrijving());
			dbEtappe.setDatum(etappe.getDatum());
			//Itereer door de stedenlijst en zet de start en finishplaats.
			List<Stad> steden = etappe.getSteden();
			logger.debug("Startplaats index = " + etappe.getStartPlaatsIndex());
			logger.debug("Finishplaats index = " + etappe.getFinishPlaatsIndex());
			for (Stad nextStad: steden) {
				if (nextStad.getId() == etappe.getStartPlaatsIndex()) {
					dbEtappe.setStartplaats(nextStad);
				}
				if (nextStad.getId() == etappe.getFinishPlaatsIndex()) {
					dbEtappe.setFinishplaats(nextStad);
				}
			}
			//Sla de etappe op
			//TODO opslaan kunnen we misschien abstracter maken door dit te laten afhandelen door de TourFacade. Dan kunnen de dao's verwijderd worden.
			if (dbEtappe instanceof StandaardEtappe) {
				standaardEtappeDao.saveStandaardEtappe((StandaardEtappe)dbEtappe);
			} else if (dbEtappe instanceof PloegenTijdrit){
				ploegenTijdritDao.savePloegenTijdrit((PloegenTijdrit)dbEtappe);
			} else {
				throw new ServletException("Etappe in sessie is niet correct.");
			}
		} else {
			throw new ServletException("Etappe in sessie is niet correct.");
		}
		return new ModelAndView(new RedirectView(getSuccessView()));
	}
	
	@Override
	/**
	 * Override de initbinder methode van de BaseCommandController van Spring om een custom date editor te registreren.
	 * 
	 * @param request
	 * @param binder
	 */
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
			//TODO bij een verkeerd opgegeven datumformaat komt er een erg lange error message. Deze is te overriden door gebruik te maken van de ResourceBundleMessageSource. Zie o.a. http://pa.rsons.org/node/10
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			CustomDateEditor editor = new CustomDateEditor(df, false);
			binder.registerCustomEditor(Date.class, editor);
	}
	
	
	/**
	 * Initialiseer het formbackingobject. In dit form is dit de etappe die uit de database geladen wordt.
	 * 
	 * 
	 */
	
	protected Object formBackingObject(HttpServletRequest request) throws ServletException, EditEtappeFormRequestException {
		dbEtappe = null;
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
    
    	//Haal de etappe op uit de database
    	EtappeCommand etappe = new EtappeCommand();
    	
    	dbEtappe = tourFacade.getEtappeWithStartAndFinish(nummer);
    	if (dbEtappe != null) {
    		etappe.setDatum(dbEtappe.getDatum());
    		etappe.setEtappenummer(dbEtappe.getEtappenummer());
    		etappe.setOmschrijving(dbEtappe.getOmschrijving());
    		etappe.setSteden(stadDao.loadAllSteden());
    		//Bepaal nu index in de lijst van de startplaats van deze etappe
    		if (dbEtappe.getStartplaats() != null) {
    			etappe.setStartPlaatsIndex(dbEtappe.getStartplaats().getId());
    		}
    		if (dbEtappe.getFinishplaats() != null) {
    			etappe.setFinishPlaatsIndex(dbEtappe.getFinishplaats().getId());
    		}
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

	/**
	 * @return the tourFacade
	 */
	public TourFacade getTourFacade() {
		return tourFacade;
	}

	/**
	 * @param tourFacade the tourFacade to set
	 */
	public void setTourFacade(TourFacade tourFacade) {
		this.tourFacade = tourFacade;
	}
	
	
	
	
	

}
