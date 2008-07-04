package nl.doyle.mccloud.tourdefrance.web.spring.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import nl.doyle.mccloud.tourdefrance.dao.StadDao;
import nl.doyle.mccloud.tourdefrance.valueobjects.Stad;
import nl.doyle.mccloud.tourdefrance.web.spring.command.StadCommand;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

public class NieuweStadFormController extends SimpleFormController {

	private static final Log logger = LogFactory.getLog(NieuweStadFormController.class);
	private StadDao stadDao;
	
	
	/**
	 * Handelt de submit van de newCity pagina af en geeft een model en view terug
	 * 
	 * @param command Het command-object dat door het form wordt gepost
	 * @return model en view
	 * @exception ServletException
	 * @see ModelAndView
	 */
	public ModelAndView onSubmit(Object command) throws ServletException {
		//int increase = ((PriceIncrease) command).getPercentage();
		StadCommand stadCommand = (StadCommand) command;
        logger.info("Creating new Stad");
        Stad saveStad = new Stad();
        logger.debug("Waardes: " + stadCommand.getLand() + ", " + stadCommand.getStad());
        saveStad.setId(stadCommand.getId());
        saveStad.setLand(stadCommand.getLand());
        logger.debug("StadCommand.getStad = " + stadCommand.getStad());
        saveStad.setStad(stadCommand.getStad());
        saveStad.setLongitude(stadCommand.getLongitude());
        saveStad.setLatitude(stadCommand.getLatitude());
        //Hier de save van de Renner aanroepen
        stadDao.saveOrUpdateStad(saveStad);
        
        logger.info("returning from NieweStadFormController view to " + getSuccessView());
        return new ModelAndView(new RedirectView(getSuccessView()));
    }

	protected Object formBackingObject(HttpServletRequest request) throws ServletException, NieuweStadFormRequestException {
//		TODO Dit moet goed geimplementeerd worden zodat er geen verkeerde waardes ingevuld kunnen worden. Security
    	Integer stadId= ServletRequestUtils.getIntParameter(request,"stadId");
    	//check of deze parameter wel is ingevuld. Zoniet dan is 'nummer' 'null'.
    	int id;
    	StadCommand stad = new StadCommand();
    	if (stadId != null) {
    		id  = stadId;
    		logger.debug("Stad ID = " + id);
    	    
        	//Haal de renner op uit de database
        	
        	Stad dbStad = stadDao.loadStad(id);
        	//Als er geen renner is dan is dat een fout. Alle renners moeten al geinstantieerd zijn.
        	//TODO Misschien moeten we dit toch op een andere manier valideren.
        	if (dbStad != null) {
        		stad.setId(dbStad.getId());
        		stad.setLand(dbStad.getLand());
        		stad.setStad(dbStad.getStad());
        		stad.setLatitude(dbStad.getLatitude());
        		stad.setLongitude(dbStad.getLongitude());
        	} 
    	}
    	
    	logger.debug("FormBackingObject geinstantieerd.");
    	return stad;
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

	
	
}
