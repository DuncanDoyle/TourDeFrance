package nl.doyle.mccloud.tourdefrance.web.spring.validator;

import nl.doyle.mccloud.tourdefrance.web.spring.command.DeelnemerCommand;
import nl.doyle.mccloud.tourdefrance.web.spring.command.InitializeGameCommand;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class InitializeGameValidator implements Validator {

	public boolean supports(Class clazz) {
		return clazz.equals(InitializeGameCommand.class);
	}

	public void validate(Object command, Errors errors) {
		
		InitializeGameCommand initGameCommand = (InitializeGameCommand) command;
		
		if (initGameCommand == null) {
			errors.rejectValue("aantalEtappes", "error.not-specified", null, "Value required.");
		}
		
		if(initGameCommand.getAantalEtappes() <= 0) {
			//errors.rejectValue("percentage", "error.not-specified", null, "Value required.");
			errors.rejectValue("aantalEtappes", "error.too-low", new Object[] {new Integer(0)}, "Value too low.");
		}
		
		if(initGameCommand.getAantalPloegen() <= 0) {
			errors.rejectValue("aantalPloegen", "error.too-low", new Object[] {new Integer(0)}, "Value too low.");
		}
		
		if (initGameCommand.getPloegenTijdritEtappeNummer() <= 0) {
			errors.rejectValue("ploegenTijdritEtappeNummer", "error.too-low", new Object[] {new Integer(0)}, "Value too low.");
		}
	}

}
