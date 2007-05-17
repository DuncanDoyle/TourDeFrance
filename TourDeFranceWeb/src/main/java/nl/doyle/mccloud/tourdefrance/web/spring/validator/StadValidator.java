package nl.doyle.mccloud.tourdefrance.web.spring.validator;

import nl.doyle.mccloud.tourdefrance.web.spring.command.StadCommand;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class StadValidator implements Validator {

	public boolean supports(Class clazz) {
		return clazz.equals(StadCommand.class);
	}

	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub

	}

}
