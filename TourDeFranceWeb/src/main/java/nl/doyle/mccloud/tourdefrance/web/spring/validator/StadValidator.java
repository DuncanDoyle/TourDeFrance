package nl.doyle.mccloud.tourdefrance.web.spring.validator;

import nl.doyle.mccloud.tourdefrance.web.spring.command.StadCommand;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public final class StadValidator implements Validator {

	@SuppressWarnings("unchecked")
	public boolean supports(final Class clazz) {
		return clazz.equals(StadCommand.class);
	}

	public void validate(final Object target, final Errors errors) {
		// TODO Auto-generated method stub

	}

}
