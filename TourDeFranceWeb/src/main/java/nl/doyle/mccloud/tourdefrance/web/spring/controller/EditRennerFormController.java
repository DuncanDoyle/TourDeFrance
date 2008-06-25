package nl.doyle.mccloud.tourdefrance.web.spring.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import nl.doyle.mccloud.tourdefrance.dao.RennerDao;
import nl.doyle.mccloud.tourdefrance.valueobjects.Renner;
import nl.doyle.mccloud.tourdefrance.web.spring.command.RennerCommand;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Form Controller which controls the editting of the racers.
 * 
 * @author Duncan Doyle
 * @since 1.0
 */

public class EditRennerFormController extends SimpleFormController {
	
	private static final Log logger = LogFactory.getLog(EditRennerFormController.class);
	private RennerDao rennerDao;
	
	
	/**
	 * Handelt de submit van de editRenner pagina af en geeft een model en view terug
	 * 
	 * @param command Het command-object dat door het form wordt gepost
	 * @return model en view
	 * @exception ServletException
	 * @see ModelAndView
	 */
	public ModelAndView onSubmit(Object command) throws ServletException {
        RennerCommand rennerCommand = (RennerCommand) command;
        logger.info("Editting Renner with values: " + rennerCommand.toString());
        Renner saveRenner = new Renner();
        saveRenner.setNummer(rennerCommand.getNummer());
        saveRenner.setVoornaam(rennerCommand.getVoornaam());
        saveRenner.setAchternaam(rennerCommand.getAchternaam());
        rennerDao.saveRenner(saveRenner);
        //Hier de update actie van de Renner aanroepen
        logger.info("returning from EditRennerForm view to " + getSuccessView());

        return new ModelAndView(new RedirectView(getSuccessView()));
    }

	
    protected Object formBackingObject(HttpServletRequest request) throws ServletException, EditRennerFormRequestException {
    	//TODO Dit moet goed geimplementeerd worden zodat er geen verkeerde waardes ingevuld kunnen worden. Security
    	Integer rennerNummer = ServletRequestUtils.getIntParameter(request,"renner");
    	//check of deze parameter wel is ingevuld. Zoniet dan is 'nummer' 'null'.
    	int nummer;
    	if (rennerNummer != null) {
    		nummer  = rennerNummer;
    	} else {
    		throw new EditRennerFormRequestException("Rennernummer niet opgegeven.");
    	}
    	logger.debug("Rennernummer = " + nummer);
    
    	//Haal de renner op uit de database
    	RennerCommand renner = new RennerCommand();
    	Renner dbRenner = rennerDao.loadRenner(nummer);
    	//Als er geen renner is dan is dat een fout. Alle renners moeten al geinstantieerd zijn.
    	//Deze pagina edit alleen maar en kan niets toevoegen
    	//TODO Misschien moeten we dit toch op een andere manier valideren.
    	if (dbRenner != null) {
    		renner.setAchternaam(dbRenner.getAchternaam());
    		renner.setVoornaam(dbRenner.getVoornaam());
    		renner.setNummer(dbRenner.getNummer());
    	} else {
    		throw new EditRennerFormRequestException("Renner niet gevonden in de database");
    	}
    	logger.debug("FormBackingObject geinstantieerd.");
    	return renner;
    }
    
	public RennerDao getRennerDao() {
		return rennerDao;
	}


	public void setRennerDao(RennerDao rennerDao) {
		this.rennerDao = rennerDao;
	}
      
	
}
