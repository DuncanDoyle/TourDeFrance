package nl.doyle.mccloud.tourdefrance.web.spring.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import nl.doyle.mccloud.tourdefrance.controller.TourFacade;
import nl.doyle.mccloud.tourdefrance.dao.PloegenTijdritDao;
import nl.doyle.mccloud.tourdefrance.dao.StadDao;
import nl.doyle.mccloud.tourdefrance.dao.StandaardEtappeDao;
import nl.doyle.mccloud.tourdefrance.valueobjects.Etappe;
import nl.doyle.mccloud.tourdefrance.valueobjects.PloegenTijdrit;
import nl.doyle.mccloud.tourdefrance.valueobjects.Stad;
import nl.doyle.mccloud.tourdefrance.valueobjects.StandaardEtappe;
import nl.doyle.mccloud.tourdefrance.web.spring.command.EtappeCommand;
import nl.doyle.mccloud.tourdefrance.web.spring.command.EtappeUitslagCommand;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractFormController;
import org.springframework.web.servlet.mvc.BaseCommandController;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/editEtappe.htm")
@SessionAttributes("etappeCommand")
public final class EditEtappeFormController {

	/**
	 * Commons-Logging logger.
	 */
	private static final Log logger = LogFactory.getLog(EditEtappeFormController.class);

	/**
	 * The controller's success view.
	 */
	private static final String SUCCESS_VIEW = "listEtappes.htm";

	/**
	 * The DAO for {@link StandaardEtappe}s.
	 */
	private StandaardEtappeDao standaardEtappeDao;

	/**
	 * The DAO for {@link PloegenTijdrit}.
	 */
	private PloegenTijdritDao ploegenTijdritDao;

	/**
	 * The DAO for {@link Stad}.
	 */
	private StadDao stadDao;

	/**
	 * The {@link Etappe} retrieved from the DB.
	 */
	private Etappe dbEtappe;

	/**
	 * The applications business service.
	 */
	private TourFacade tourFacade;

	/**
	 * Processes the form submit. Stores the {@link Etappe}.
	 * 
	 * @param etappeCommand
	 *            the updated command object retrieved from the form values
	 * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(Object)
	 */
	@RequestMapping(method = RequestMethod.POST)
	protected ModelAndView onSubmit(@ModelAttribute("etappeCommand") final EtappeCommand etappeCommand) throws ServletException {
		/*
		 * Deze volledige code gaat met updaten goed omdat de Uitslagen sets niet geinitialiseerd zijn door Hibernate en daardoor tijdens de
		 * update waarschijnlijk ook niet worden aangepast. In het deelnemer scherm ging dit fout omdat we daar het scherm volledig los
		 * hebben gekoppeld van Hibernate en bij het opslaan een nieuw object aanmaken. Hierdoor gingen bij de Deelnemer de referenties naar
		 * de verschillende renners verloren.
		 */
		if (logger.isDebugEnabled()) {
			logger.debug("Saving etappe!!");
		}

		// TODO Validator implementeren
		// Alle validatie is al gedaan door de validator. Nu de etappe opslaan
		// Eerst verschillen bepalen tussen object uit DB en ingevulde waardes
		dbEtappe.setOmschrijving(etappeCommand.getOmschrijving());
		dbEtappe.setDatum(etappeCommand.getDatum());
		// Itereer door de stedenlijst en zet de start en finishplaats.
		List<Stad> steden = etappeCommand.getSteden();
		logger.debug("Startplaats index = " + etappeCommand.getStartPlaatsIndex());
		logger.debug("Finishplaats index = " + etappeCommand.getFinishPlaatsIndex());
		for (Stad nextStad : steden) {
			if (nextStad.getId() == etappeCommand.getStartPlaatsIndex()) {
				dbEtappe.setStartplaats(nextStad);
			}
			if (nextStad.getId() == etappeCommand.getFinishPlaatsIndex()) {
				dbEtappe.setFinishplaats(nextStad);
			}
		}
		// Sla de etappe op
		// TODO opslaan kunnen we misschien abstracter maken door dit te laten afhandelen door de TourFacade. Dan kunnen de dao's verwijderd
		// worden.
		if (dbEtappe instanceof StandaardEtappe) {
			standaardEtappeDao.saveStandaardEtappe((StandaardEtappe) dbEtappe);
		} else if (dbEtappe instanceof PloegenTijdrit) {
			ploegenTijdritDao.savePloegenTijdrit((PloegenTijdrit) dbEtappe);
		} else {
			throw new ServletException("Etappe in sessie is niet correct.");
		}

		return new ModelAndView(new RedirectView(SUCCESS_VIEW));
	}

	/**
	 * Overrides the {@link BaseCommandController#initBinder(HttpServletRequest, ServletRequestDataBinder)} method to regisrer a custom date
	 * editor.
	 * 
	 * @param request
	 *            the http servlet request
	 * @param binder
	 *            the servlet request data binder used to register a custom editor
	 */
	@InitBinder
	protected void initBinder(final WebDataBinder binder) throws Exception {
		// TODO bij een verkeerd opgegeven datumformaat komt er een erg lange error message. Deze is te overriden door gebruik te maken van
		// de ResourceBundleMessageSource. Zie o.a. http://pa.rsons.org/node/10
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		CustomDateEditor editor = new CustomDateEditor(df, false);
		binder.registerCustomEditor(Date.class, editor);
	}

	/**
	 * Creates the forms backing object. For this form this is the {@link EtappeUitslagCommand}. The values set are retrieved from the stage
	 * number servlet request parameter.
	 * 
	 * @param request
	 *            the http servlet request from which the request params are retrieved
	 * 
	 * @see AbstractFormController#formBackingObject(HttpServletRequest)
	 */
	@RequestMapping(method = RequestMethod.GET)
	@ModelAttribute("etappeCommand")
	protected EtappeCommand formBackingObject(@RequestParam("etappe") final Integer etappeNummer) throws ServletException, EditEtappeFormRequestException {
		dbEtappe = null;
		// TODO Dit moet goed geimplementeerd worden zodat er geen verkeerde waardes ingevuld kunnen worden. Security
		// check of deze parameter wel is ingevuld. Zoniet dan is 'nummer' 'null'.
		if (etappeNummer == null) {
			throw new EditEtappeFormRequestException("Etappenummer niet opgegeven.");
		}
		logger.debug("Etappenummer = " + etappeNummer);

		// Haal de etappe op uit de database
		EtappeCommand etappe = new EtappeCommand();

		dbEtappe = tourFacade.getEtappeWithStartAndFinish(etappeNummer);
		if (dbEtappe != null) {
			etappe.setDatum(dbEtappe.getDatum());
			etappe.setEtappenummer(dbEtappe.getEtappenummer());
			etappe.setOmschrijving(dbEtappe.getOmschrijving());
			etappe.setSteden(stadDao.loadAllSteden());
			// Bepaal nu index in de lijst van de startplaats van deze etappe
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
	 * @param ploegenTijdritDao
	 *            the ploegenTijdritDao to set
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
	 * @param standaardEtappeDao
	 *            the standaardEtappeDao to set
	 */
	public void setStandaardEtappeDao(final StandaardEtappeDao standaardEtappeDao) {
		this.standaardEtappeDao = standaardEtappeDao;
	}

	/**
	 * @return the stadDao
	 */
	public StadDao getStadDao() {
		return stadDao;
	}

	/**
	 * @param stadDao
	 *            the stadDao to set
	 */
	public void setStadDao(final StadDao stadDao) {
		this.stadDao = stadDao;
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

}
