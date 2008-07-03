package nl.doyle.mccloud.tourdefrance.web.spring.controller;

import javax.servlet.ServletException;

import nl.doyle.mccloud.tourdefrance.setup.GameSetupController;
import nl.doyle.mccloud.tourdefrance.web.spring.command.InitializeGameCommand;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

public class InitializeGameFormController extends SimpleFormController {

	private static final Log logger = LogFactory.getLog(InitializeGameFormController.class);
	private GameSetupController gameSetupController;
	
	
	/**
	 * Handelt de submit van de initializeGame pagina af en geeft een model en view terug
	 * 
	 * @param command Het command-object dat door het form wordt gepost
	 * @return model en view
	 * @exception ServletException
	 * @see ModelAndView
	 */
	public ModelAndView onSubmit(Object command) throws ServletException {
		
		InitializeGameCommand initGameCommand = (InitializeGameCommand) command;
		logger.debug("Initializing Game");
		gameSetupController.initializeGame(initGameCommand.getAantalPloegen(), initGameCommand.getAantalEtappes(), initGameCommand.getPloegenTijdritEtappeNummer());
		logger.info("Game initialized");
		return new ModelAndView(new RedirectView(getSuccessView()));
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

	
    //protected Object formBackingObject(HttpServletRequest request) throws ServletException, EditRennerFormRequestException {
    //}
    
	
      
	
}
