package nl.doyle.mccloud.tourdefrance.web.spring.validator;

import nl.doyle.mccloud.tourdefrance.web.spring.command.UitslagBedragCommand;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class UitslagBedragValidator implements Validator {

	private static final Log logger = LogFactory.getLog(UitslagBedragValidator.class);
	
	public boolean supports(Class clazz) {
		return clazz.equals(UitslagBedragCommand.class);
	}

	public void validate(Object arg0, Errors arg1) {
		// TODO Auto-generated method stub

	}

}
