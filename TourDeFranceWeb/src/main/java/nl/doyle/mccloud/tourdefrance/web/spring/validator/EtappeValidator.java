package nl.doyle.mccloud.tourdefrance.web.spring.validator;

import nl.doyle.mccloud.tourdefrance.web.spring.command.EtappeCommand;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public final class EtappeValidator implements Validator {

	private static final Log logger = LogFactory.getLog(EtappeValidator.class);
	
	@SuppressWarnings("unchecked")
	public boolean supports(final Class clazz) {
		return clazz.equals(EtappeCommand.class);
	}
	
	public void validate(final Object target, final Errors errors) {
		
		// TODO Auto-generated method stub
		 
		/*
		RennerCommand rc = (RennerCommand) obj;
        if (rc == null) {
            errors.rejectValue("percentage", "error.not-specified", null, "Value required.");
        }
        else {
            logger.info("Validating with " + pi + ": " + pi.getPercentage());
            if (pi.getPercentage() > maxPercentage) {
                errors.rejectValue("percentage", "error.too-high",
                    new Object[] {new Integer(maxPercentage)}, "Value too high.");
            }
            if (pi.getPercentage() <= minPercentage) {
                errors.rejectValue("percentage", "error.too-low",
                    new Object[] {new Integer(minPercentage)}, "Value too low.");
            }
        }
		*/
		logger.debug("Aan het validaten");
	}
}
