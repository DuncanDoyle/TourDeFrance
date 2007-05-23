package nl.doyle.mccloud.tourdefrance.web.spring.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import nl.doyle.mccloud.tourdefrance.dao.DeelnemerDao;
import nl.doyle.mccloud.tourdefrance.valueobjects.Deelnemer;
import nl.doyle.mccloud.tourdefrance.web.spring.command.DeelnemerCommand;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

public class EditDeelnemerFormController extends SimpleFormController implements
		Controller {

	private static final Log logger = LogFactory.getLog(EditDeelnemerFormController.class);
	private DeelnemerDao deelnemerDao;
	
	
	public ModelAndView onSubmit(Object command) throws ServletException {
        DeelnemerCommand deelnemerCommand = (DeelnemerCommand) command;
        logger.info("Editting Renner with values: " + deelnemerCommand.toString());
        //Hier de update actie van de Deelnemer aanroepen
        deelnemerDao.saveDeelnemerDataExcludingForeignKeys(deelnemerCommand.getNummer(), 
        													deelnemerCommand.getVoornaam(), 
        													deelnemerCommand.getAchternaam(), 
        													deelnemerCommand.getEmail(), 
        													deelnemerCommand.getRekeningnummer());
        logger.info("returning from EditRennerForm view to " + getSuccessView());

        return new ModelAndView(new RedirectView(getSuccessView()));
    }

	
    protected Object formBackingObject(HttpServletRequest request) throws ServletException, EditDeelnemerFormRequestException {
    	//TODO Dit moet goed geimplementeerd worden zodat er geen verkeerde waardes ingevuld kunnen worden. Security
    	Integer deelnemerNummer = ServletRequestUtils.getIntParameter(request,"deelnemer");
    	//check of deze parameter wel is ingevuld. Zoniet dan is 'nummer' 'null'.
    	int nummer;
    	if (deelnemerNummer != null) {
    		nummer  = deelnemerNummer;
    	} else {
    		throw new EditDeelnemerFormRequestException("Rennernummer niet opgegeven.");
    	}
    	logger.debug("Deelnemernummer = " + nummer);
    
    	//Haal de renner op uit de database
    	DeelnemerCommand deelnemer = new DeelnemerCommand();
    	Deelnemer dbDeelnemer = deelnemerDao.loadDeelnemer(nummer);
    	//Als er geen renner is dan is dat een fout. Alle renners moeten al geinstantieerd zijn.
    	//Deze pagina edit alleen maar en kan niets toevoegen
    	//TODO Misschien moeten we dit toch op een andere manier valideren.
    	if (dbDeelnemer != null) {
    		deelnemer.setAchternaam(dbDeelnemer.getAchternaam());
    		deelnemer.setVoornaam(dbDeelnemer.getVoornaam());
    		deelnemer.setNummer(dbDeelnemer.getNummer());
    		deelnemer.setEmail(dbDeelnemer.getEmail());
    		deelnemer.setRekeningnummer(dbDeelnemer.getRekeningnummer());
    	} else {
    		throw new EditDeelnemerFormRequestException("Deelnemer niet gevonden in de database");
    	}
    	logger.debug("FormBackingObject geinstantieerd.");
    	return deelnemer;
    }


	/**
	 * @return the deelnemerDao
	 */
	public DeelnemerDao getDeelnemerDao() {
		return deelnemerDao;
	}


	/**
	 * @param deelnemerDao the deelnemerDao to set
	 */
	public void setDeelnemerDao(DeelnemerDao deelnemerDao) {
		this.deelnemerDao = deelnemerDao;
	}
	
    
}
