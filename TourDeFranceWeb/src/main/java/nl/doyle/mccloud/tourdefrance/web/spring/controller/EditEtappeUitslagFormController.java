package nl.doyle.mccloud.tourdefrance.web.spring.controller;

import javax.servlet.http.HttpServletRequest;

import nl.doyle.mccloud.tourdefrance.config.TourConfig;
import nl.doyle.mccloud.tourdefrance.controller.TourFacade;
import nl.doyle.mccloud.tourdefrance.dao.RennerDao;
import nl.doyle.mccloud.tourdefrance.valueobjects.AbstractEtappeAndEindUitslag;
import nl.doyle.mccloud.tourdefrance.valueobjects.Renner;
import nl.doyle.mccloud.tourdefrance.web.spring.command.EtappeUitslagCommand;
import nl.doyle.mccloud.tourdefrance.web.spring.controller.visitor.EditEtappeUitslagFormBackingObjectVisitor;
import nl.doyle.mccloud.tourdefrance.web.spring.controller.visitor.EditEtappeUitslagOnSubmitVisitor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractFormController;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Controller which processes the editting of the stage results.
 * 
 * @author Duncan Doyle
 * @since 0.1
 */
@Controller
@RequestMapping("/editEtappeUitslag.htm")
@SessionAttributes("etappeUitslagCommand")
public final class EditEtappeUitslagFormController {

	/**
	 * Commons-Logging logger.
	 */
	private static final Log LOG = LogFactory.getLog(EditEtappeUitslagFormController.class);

	/**
	 * The controller's success view.
	 */
	private static final String SUCCESS_VIEW = "listEtappeUitslag.htm";
	
	/**
	 * The tour configuration object.
	 */
	private TourConfig config;

	/**
	 * The DAO for {@link Renner}.
	 */
	private RennerDao rennerDao;
	
	/**
	 * The {@link AbstractEtappeAndEindUitslag} retrieved from the DB.
	 */
	private AbstractEtappeAndEindUitslag dbEtappe;
	
	/**
	 * The business services interface.
	 */
	private TourFacade tourFacade;

	/**
	 * Called when the form is submitted. Sets the values stored in the command object on the form-backing object. Saves this object in the
	 * DB. Returns the new {@link ModelAndView}.
	 * 
	 * @param etappeUitslagCommand
	 *            the command object filled by the form
	 * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(Object)
	 */
	@RequestMapping(method = RequestMethod.POST)
	protected ModelAndView onSubmit(@ModelAttribute("etappeUitslagCommand") final EtappeUitslagCommand etappeUitslagCommand) throws Exception {
		// Cast command object.
		EtappeUitslagCommand etappeUitslag = (EtappeUitslagCommand) etappeUitslagCommand;
		EditEtappeUitslagOnSubmitVisitor visitor = new EditEtappeUitslagOnSubmitVisitor(etappeUitslag, rennerDao);
		// The visitor will set the values on the dbEtappe.
		dbEtappe.accept(visitor);
		// save the stage or endresult
		tourFacade.saveEtappe(dbEtappe);
		// redirect to the succesview.
		return new ModelAndView(new RedirectView(SUCCESS_VIEW + "?etappe=" + etappeUitslag.getEtappenummer()));
	}

	/**
	 * Creates the forms backing object. For this form this is the {@link EtappeUitslagCommand}. The values set are retrieved from the stage
	 * number servlet request parameter.
	 * 
	 * @param request
	 *            the http servlet request from which the request params are retrieved
	 * @see AbstractFormController#formBackingObject(HttpServletRequest)
	 */
	@RequestMapping(method = RequestMethod.GET)
	@ModelAttribute("etappeUitslagCommand")
	protected EtappeUitslagCommand formBackingObject(@RequestParam("etappe") final Integer etappeNummer) throws Exception {
		LOG.debug("Instantiating formBackingObject");
		// Zet het Etappe object op null zodat de waarde uit hetzelfde scherm in dezelfde sessie verwijderd wordt.
		dbEtappe = null;

		// TODO Dit moet goed geimplementeerd worden zodat er geen verkeerde waardes ingevuld kunnen worden. Security
		
		// check of deze parameter wel is ingevuld. Zoniet dan is 'nummer' 'null'.
		if (etappeNummer == null) {
			throw new EditEtappeFormRequestException("Etappenummer niet opgegeven.");
		}
		LOG.debug("Etappenummer = " + etappeNummer);
		// Haal de etappe op uit de database
		dbEtappe = tourFacade.getEtappeWithUitslag(etappeNummer);
		EtappeUitslagCommand etappe = null;

		if (dbEtappe != null) {
			// Maak een nieuw FormBackingObject aan
			LOG.debug("Instantiating new EtappeUitslagCommand.");
			// create the visitor which will initialize the form backing object
			EditEtappeUitslagFormBackingObjectVisitor visitor = new EditEtappeUitslagFormBackingObjectVisitor(rennerDao, config);
			dbEtappe.accept(visitor);
			// the formbacking object should now be initialized.
			etappe = visitor.getStageResultCommand();

		} else {
			throw new EditEtappeUitslagFormRequestException("Etappe niet gevonden in database.");
		}
		LOG.debug("FormBackingObject geinstantieerd.");
		return etappe;
	}

	/**
	 * @return the rennerDao
	 */
	public RennerDao getRennerDao() {
		return rennerDao;
	}

	/**
	 * @param rennerDao
	 *            the rennerDao to set
	 */
	public void setRennerDao(final RennerDao rennerDao) {
		this.rennerDao = rennerDao;
	}

	/**
	 * @return the dbEtappe
	 */
	public AbstractEtappeAndEindUitslag getDbEtappe() {
		return dbEtappe;
	}

	/**
	 * @param dbEtappe
	 *            the dbEtappe to set
	 */
	public void setDbEtappe(final AbstractEtappeAndEindUitslag dbEtappe) {
		this.dbEtappe = dbEtappe;
	}

	/**
	 * @return the tourFacade
	 */
	public TourFacade getTourFacade() {
		return tourFacade;
	}

	/**
	 * @param tourFacade
	 *            the tourFacade to set
	 */
	public void setTourFacade(final TourFacade tourFacade) {
		this.tourFacade = tourFacade;
	}

	/**
	 * @return the config
	 */
	public TourConfig getConfig() {
		return config;
	}

	/**
	 * @param config
	 *            the config to set
	 */
	public void setConfig(final TourConfig config) {
		this.config = config;
	}

}
