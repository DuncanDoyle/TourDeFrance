package nl.doyle.mccloud.tourdefrance.web.spring.controller;

import javax.servlet.ServletException;

import nl.doyle.mccloud.tourdefrance.dao.StadDao;
import nl.doyle.mccloud.tourdefrance.valueobjects.Stad;
import nl.doyle.mccloud.tourdefrance.web.spring.command.StadCommand;

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
// RequestMapping for nieuweStad.htm and editStad.htm
@RequestMapping("/*Stad.htm")
@SessionAttributes("stadCommand")
public class NieuweStadFormController {

	private static final Log logger = LogFactory.getLog(NieuweStadFormController.class);
	private StadDao stadDao;

	/**
	 * The controller's success view.
	 */
	private static final String SUCCESS_VIEW = "listSteden.htm";

	/**
	 * Handelt de submit van de newCity pagina af en geeft een model en view terug
	 * 
	 * @param stadCommand
	 *            Het command-object dat door het form wordt gepost
	 * @return model en view
	 * @exception ServletException
	 * @see ModelAndView
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView onSubmit(@ModelAttribute("stadCommand") final StadCommand stadCommand) throws ServletException {
		logger.info("Creating new Stad");
		Stad saveStad = new Stad();
		logger.debug("Waardes: " + stadCommand.getLand() + ", " + stadCommand.getStad());
		saveStad.setId(stadCommand.getId());
		saveStad.setLand(stadCommand.getLand());
		logger.debug("StadCommand.getStad = " + stadCommand.getStad());
		saveStad.setStad(stadCommand.getStad());
		saveStad.setLongitude(stadCommand.getLongitude());
		saveStad.setLatitude(stadCommand.getLatitude());
		// Hier de save van de Renner aanroepen
		stadDao.saveOrUpdateStad(saveStad);

		logger.info("returning from NieweStadFormController view to " + SUCCESS_VIEW);
		return new ModelAndView(new RedirectView(SUCCESS_VIEW));
	}

	@RequestMapping(method = RequestMethod.GET)
	@ModelAttribute("stadCommand")
	protected ModelAndView formBackingObject(@RequestParam(value = "stadId", required = false) final Integer stadId)
			throws NieuweStadFormRequestException {
		// TODO Dit moet goed geimplementeerd worden zodat er geen verkeerde waardes ingevuld kunnen worden. Security

		// check of deze parameter wel is ingevuld. Zoniet dan is 'nummer' 'null'.
		int id;
		StadCommand stad = new StadCommand();
		if (stadId != null) {
			id = stadId;
			logger.debug("Stad ID = " + id);

			// Haal de renner op uit de database

			Stad dbStad = stadDao.loadStad(id);
			// Als er geen renner is dan is dat een fout. Alle renners moeten al geinstantieerd zijn.
			// TODO Misschien moeten we dit toch op een andere manier valideren.
			if (dbStad != null) {
				stad.setId(dbStad.getId());
				stad.setLand(dbStad.getLand());
				stad.setStad(dbStad.getStad());
				stad.setLatitude(dbStad.getLatitude());
				stad.setLongitude(dbStad.getLongitude());
			}
		}

		logger.debug("FormBackingObject geinstantieerd.");
		/*
		 * Returning ModelAndView. This controller can be accessed from multiple URLs, but has to show a single page. So we can't rely on
		 * the default view name resolver.return stad;
		 */
		ModelAndView stadModelAndView = new ModelAndView("nieuweStad");
		stadModelAndView.addObject("stadCommand", stad);
		return stadModelAndView;
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

}
