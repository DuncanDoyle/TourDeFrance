package nl.doyle.mccloud.tourdefrance.web.spring.controller;

import javax.servlet.ServletException;

import nl.doyle.mccloud.tourdefrance.setup.GameSetupController;
import nl.doyle.mccloud.tourdefrance.web.spring.command.InitializeGameCommand;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Controller which initializes the / a new game.
 * 
 * @author Duncan Doyle
 * @since 0.1
 */
@Controller
@RequestMapping("/initializeGame.htm")
public final class InitializeGameFormController {

	/**
	 * Commons-Logging logger.
	 */
	private static final Log logger = LogFactory.getLog(InitializeGameFormController.class);

	/**
	 * The controller's success view.
	 */
	private static final String SUCCESS_VIEW = "adminPage.htm";

	/**
	 * The application's setup controller.
	 */
	private GameSetupController gameSetupController;

	/**
	 * Handelt de submit van de initializeGame pagina af en geeft een model en view terug
	 * 
	 * @param initGameCommand
	 *            Het command-object dat door het form wordt gepost
	 * @return model en view
	 * @exception ServletException
	 * @see ModelAndView
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView onSubmit(@ModelAttribute("initializeGameCommand") final InitializeGameCommand initGameCommand)
			throws ServletException {

		logger.debug("Initializing Game");
		gameSetupController.initializeGame(initGameCommand.getAantalPloegen(), initGameCommand.getAantalEtappes(), initGameCommand
				.getPloegenTijdritEtappeNummer());
		logger.info("Game initialized");
		return new ModelAndView(new RedirectView(SUCCESS_VIEW));
	}

	/**
	 * By convention the <code>initializeGame</code> view will be returned.
	 */
	@RequestMapping(method = RequestMethod.GET)
	@ModelAttribute("initializeGameCommand")
	public InitializeGameCommand handleRequest() {
		return new InitializeGameCommand();
	}

	/**
	 * @return the gameSetupController
	 */
	public GameSetupController getGameSetupController() {
		return gameSetupController;
	}

	/**
	 * @param gameSetupController
	 *            the gameSetupController to set
	 */
	public void setGameSetupController(final GameSetupController gameSetupController) {
		this.gameSetupController = gameSetupController;
	}

}
