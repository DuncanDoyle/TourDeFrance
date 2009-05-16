package nl.doyle.mccloud.tourdefrance.web.spring.controller;

import javax.servlet.ServletException;

import nl.doyle.mccloud.tourdefrance.dao.DeelnemerDao;
import nl.doyle.mccloud.tourdefrance.valueobjects.Deelnemer;
import nl.doyle.mccloud.tourdefrance.web.spring.command.DeelnemerCommand;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/editDeelnemer.htm")
@SessionAttributes("deelnemerCommand")
public class EditDeelnemerFormController {

	private static final Log LOG = LogFactory.getLog(EditDeelnemerFormController.class);
	private DeelnemerDao deelnemerDao;

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView onSubmit(@ModelAttribute("deelnemerCommand") final DeelnemerCommand command) throws ServletException {
		DeelnemerCommand deelnemerCommand = (DeelnemerCommand) command;
		LOG.info("Editting Deelnemer with values: " + deelnemerCommand.toString());
		// Hier de update actie van de Deelnemer aanroepen
		deelnemerDao.saveDeelnemerDataExcludingForeignKeys(deelnemerCommand.getNummer(), deelnemerCommand.getVoornaam(), deelnemerCommand
				.getAchternaam(), deelnemerCommand.getEmail(), deelnemerCommand.getRekeningnummer());

		return new ModelAndView(new RedirectView("listDeelnemers.htm"));
	}

	@RequestMapping(method = RequestMethod.GET)
	@ModelAttribute("deelnemerCommand")
	protected DeelnemerCommand formBackingObject(@RequestParam("deelnemer") final Integer deelnemerNummer) throws ServletException,
			EditDeelnemerFormRequestException {
		// TODO Dit moet goed geimplementeerd worden zodat er geen verkeerde waardes ingevuld kunnen worden. Security

		// check of deze parameter wel is ingevuld. Zoniet dan is 'nummer' 'null'.
		int nummer;
		if (deelnemerNummer != null) {
			nummer = deelnemerNummer;
		} else {
			throw new EditDeelnemerFormRequestException("Rennernummer niet opgegeven.");
		}
		LOG.debug("Deelnemernummer = " + nummer);

		// Haal de renner op uit de database
		DeelnemerCommand deelnemer = new DeelnemerCommand();
		Deelnemer dbDeelnemer = deelnemerDao.loadDeelnemer(nummer);
		// Als er geen renner is dan is dat een fout. Alle renners moeten al geinstantieerd zijn.
		// Deze pagina edit alleen maar en kan niets toevoegen
		// TODO Misschien moeten we dit toch op een andere manier valideren.
		if (dbDeelnemer != null) {
			deelnemer.setAchternaam(dbDeelnemer.getAchternaam());
			deelnemer.setVoornaam(dbDeelnemer.getVoornaam());
			deelnemer.setNummer(dbDeelnemer.getNummer());
			deelnemer.setEmail(dbDeelnemer.getEmail());
			deelnemer.setRekeningnummer(dbDeelnemer.getRekeningnummer());
		} else {
			throw new EditDeelnemerFormRequestException("Deelnemer niet gevonden in de database");
		}
		LOG.debug("FormBackingObject geinstantieerd.");
		return deelnemer;
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
