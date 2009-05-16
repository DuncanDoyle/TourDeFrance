package nl.doyle.mccloud.tourdefrance.web.spring.controller;

import javax.servlet.ServletException;

import nl.doyle.mccloud.tourdefrance.dao.RennerDao;
import nl.doyle.mccloud.tourdefrance.valueobjects.Renner;
import nl.doyle.mccloud.tourdefrance.web.spring.command.RennerCommand;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Form Controller which controls the editting of the racers.
 * 
 * @author Duncan Doyle
 * @since 1.0
 */
@Controller
@RequestMapping("/editRenner.htm")
@SessionAttributes("rennerCommand")
public class EditRennerFormController {

	/**
	 * Commons-Logging logger.
	 */
	private static final Log LOG = LogFactory.getLog(EditRennerFormController.class);

	/**
	 * DAO for {@link Renner} objects.
	 */
	private RennerDao rennerDao;

	/**
	 * Handelt de submit van de editRenner pagina af en geeft een model en view terug
	 * 
	 * @param command
	 *            Het command-object dat door het form wordt gepost
	 * @return model en view
	 * @exception ServletException
	 * @see ModelAndView
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView onSubmit(@ModelAttribute("rennerCommand") final RennerCommand command) throws ServletException {
		RennerCommand rennerCommand = (RennerCommand) command;
		LOG.info("Editting Renner with values: " + rennerCommand.toString());
		Renner saveRenner = new Renner();
		saveRenner.setNummer(rennerCommand.getNummer());
		saveRenner.setVoornaam(rennerCommand.getVoornaam());
		saveRenner.setAchternaam(rennerCommand.getAchternaam());
		rennerDao.saveRenner(saveRenner);
		return new ModelAndView(new RedirectView("listRenners.htm"));
	}

	@RequestMapping(method = RequestMethod.GET)
	@ModelAttribute("rennerCommand")
	protected RennerCommand formBackingObject(@RequestParam("renner") final Integer rennerNummer) throws EditRennerFormRequestException {
		// TODO Dit moet goed geimplementeerd worden zodat er geen verkeerde waardes ingevuld kunnen worden. Security
		
		// check of deze parameter wel is ingevuld. Zoniet dan is 'nummer' 'null'.
		int nummer;
		if (rennerNummer != null) {
			nummer = rennerNummer;
		} else {
			throw new EditRennerFormRequestException("Rennernummer niet opgegeven.");
		}
		LOG.debug("Rennernummer = " + nummer);

		// Haal de renner op uit de database
		RennerCommand renner = new RennerCommand();
		Renner dbRenner = rennerDao.loadRenner(nummer);
		// Als er geen renner is dan is dat een fout. Alle renners moeten al geinstantieerd zijn.
		// Deze pagina edit alleen maar en kan niets toevoegen
		// TODO Misschien moeten we dit toch op een andere manier valideren.
		if (dbRenner != null) {
			renner.setAchternaam(dbRenner.getAchternaam());
			renner.setVoornaam(dbRenner.getVoornaam());
			renner.setNummer(dbRenner.getNummer());
		} else {
			throw new EditRennerFormRequestException("Renner niet gevonden in de database");
		}
		LOG.debug("FormBackingObject geinstantieerd.");
		return renner;
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

}
