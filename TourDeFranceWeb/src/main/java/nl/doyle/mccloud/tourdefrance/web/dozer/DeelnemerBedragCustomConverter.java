package nl.doyle.mccloud.tourdefrance.web.dozer;

import java.text.NumberFormat;
import java.util.Locale;

import net.sf.dozer.util.mapping.MappingException;
import net.sf.dozer.util.mapping.converters.CustomConverter;
import nl.doyle.mccloud.tourdefrance.dto.DeelnemerBedragDto;
import nl.doyle.mccloud.tourdefrance.web.spring.model.DeelnemerAndBedragModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DeelnemerBedragCustomConverter implements CustomConverter {

private static final Log logger = LogFactory.getLog(DeelnemerBedragCustomConverter.class);
	
	@SuppressWarnings("unchecked")
	public Object convert(final Object destination, final Object source, final Class destClass, final Class sourceClass) {
	    //TODO Hier moet nog beter gechecked worden op types. Als types niet kloppen dan moet een Dozer exception gegooid worden.
		if (source == null) {
	      return null;
	    }
	    DeelnemerAndBedragModel dest = null;
	    //Instance of checking werkt niet met generics. Daarom checken we eerst of de source een lijst
	    //is en later of de waardes in die lijst ook daadwerkelijk van het juiste type is.
	    if (source instanceof DeelnemerBedragDto) {
	    	// check to see if the object already exists
	    	if (destination == null) {
	    		dest = new DeelnemerAndBedragModel();
	    	} else {
	    		dest = (DeelnemerAndBedragModel) destination;
	    	}
	    	logger.debug("Setting locale");
	    	Locale dutch = new Locale("nl", "NL");
	    	NumberFormat format = NumberFormat.getInstance(dutch);
	    	format.setMinimumFractionDigits(2);
	    	logger.debug("Mapping DeelnemerBedragDto to DeelnemerAndBedragModel");
	    	dest.setNummer(((DeelnemerBedragDto) source).getNummer());
	    	dest.setVoornaam(((DeelnemerBedragDto) source).getVoornaam());
	    	dest.setAchternaam(((DeelnemerBedragDto) source).getAchternaam());
	    	dest.setEmail(((DeelnemerBedragDto) source).getEmail());
	    	dest.setRekeningnummer(((DeelnemerBedragDto) source).getRekeningnummer());
	    	dest.setGewonnenBedrag(format.format(((DeelnemerBedragDto) source).getGewonnenBedrag()));
	    	
	    	logger.debug("Mapping succesfull");
	    } else {
	    	throw new MappingException("Converter DeelnemerBedragCustomConverter used incorrectly. Arguments passed in were:"
	          + destination + " and " + source);
	    }
	    return dest;
	}     
}
