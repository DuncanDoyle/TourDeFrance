package nl.doyle.mccloud.tourdefrance.web.spring.validator;

import nl.doyle.mccloud.tourdefrance.web.spring.command.UitslagBedragCommand;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public final class UitslagBedragValidator implements Validator {

	@SuppressWarnings("unchecked")
	public boolean supports(final Class clazz) {
		return clazz.equals(UitslagBedragCommand.class);
	}

	public void validate(final Object arg0, final Errors arg1) {
		// TODO Auto-generated method stub

	}

}
