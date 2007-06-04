package nl.doyle.mccloud.tourdefrance.web.spring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.doyle.mccloud.tourdefrance.setup.GameSetupController;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.view.RedirectView;

public class GenerateTeamsController implements Controller {

	private static Log logger = LogFactory.getLog(GenerateTeamsController.class);
	private GameSetupController gameSetupController;
	
	public ModelAndView handleRequest(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {

		logger.debug("Generating teams");
		gameSetupController.generateDeelnemerTeams();
		logger.info("Teams generated");
		
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
	public void setGameSetupController(GameSetupController gameSetupController) {
		this.gameSetupController = gameSetupController;
	}
	
}
