package nl.doyle.mccloud.tourdefrance.web.spring.controller;

import nl.doyle.mccloud.tourdefrance.setup.GameSetupController;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/generateTeams.htm")
public class GenerateTeamsController {

	private static Log LOG = LogFactory.getLog(GenerateTeamsController.class);
	private GameSetupController gameSetupController;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView handleRequest() {

		LOG.debug("Generating teams");
		gameSetupController.generateDeelnemerTeams();
		LOG.info("Teams generated");
		
		return new ModelAndView(new RedirectView("adminPage.htm"));
	}

	/**
	 * @return the gameSetupController
	 */
	public GameSetupController getGameSetupController() {
		return gameSetupController;
	}

	/**
	 * @param gameSetupController the gameSetupController to set
	 */
	public void setGameSetupController(final GameSetupController gameSetupController) {
		this.gameSetupController = gameSetupController;
	}
	
}
