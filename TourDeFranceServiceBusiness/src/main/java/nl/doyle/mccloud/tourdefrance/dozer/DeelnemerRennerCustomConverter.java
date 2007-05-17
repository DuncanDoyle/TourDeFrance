package nl.doyle.mccloud.tourdefrance.dozer;

import java.util.ArrayList;
import java.util.List;

import net.sf.dozer.util.mapping.MappingException;
import net.sf.dozer.util.mapping.converters.CustomConverter;
import nl.doyle.mccloud.tourdefrance.dto.DeelnemerRennerDto;
import nl.doyle.mccloud.tourdefrance.valueobjects.Deelnemer;
import nl.doyle.mccloud.tourdefrance.valueobjects.Renner;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DeelnemerRennerCustomConverter implements CustomConverter {
	
	private static final Log logger = LogFactory.getLog(DeelnemerRennerCustomConverter.class);
	
	public Object convert(Object destination, Object source, Class destClass, Class sourceClass) {
	    //TODO Hier moet nog beter gechecked worden op types. Als types niet kloppen dan moet een Dozer exception gegooid worden.
		if (source == null) {
	      return null;
	    }
	    List<DeelnemerRennerDto> dest = null;
	    
	    //Instance of checking werkt niet met generics. Daarom checken we eerst of de source een lijst
	    //is en later of de waardes in die lijst ook daadwerkelijk van het juiste type is.
	    if (source instanceof Deelnemer) {
	    	// check to see if the object already exists
	    	
	    	if (destination == null) {
	    		dest = new ArrayList<DeelnemerRennerDto>();
	    	} else {
	    		dest = (ArrayList<DeelnemerRennerDto>) destination;
	    	}
	      for(Renner nextRenner: ((Deelnemer) source).getRenners()) {
	    		DeelnemerRennerDto currentDto = new DeelnemerRennerDto(); 
	    		currentDto.setRennerNummer(nextRenner.getNummer());
	    		currentDto.setRennerVoornaam(nextRenner.getVoornaam());
	    		currentDto.setRennerAchternaam(nextRenner.getAchternaam());
	    		currentDto.setDeelnemerNummer(((Deelnemer)source).getNummer());
	    		currentDto.setDeelnemerVoornaam(((Deelnemer)source).getVoornaam());
	    		currentDto.setDeelnemerAchternaam(((Deelnemer)source).getAchternaam());
	    		currentDto.setDeelnemerEmail(((Deelnemer)source).getEmail());
	    		currentDto.setDeelnemerRekeningnummer(((Deelnemer)source).getRekeningnummer());
	    		dest.add(currentDto);
	    	}
	    } else {
	    	throw new MappingException("Converter DeelnemerRennerCustomConverter used incorrectly. Arguments passed in were:"
	          + destination + " and " + source);
	    }
	    return dest;
	}     

}
