package nl.doyle.mccloud.tourdefrance.web.dozer;

import java.util.ArrayList;
import java.util.List;

import net.sf.dozer.util.mapping.MappingException;
import net.sf.dozer.util.mapping.converters.CustomConverter;
import nl.doyle.mccloud.tourdefrance.dto.DeelnemerWithRennersDto;
import nl.doyle.mccloud.tourdefrance.dto.RennerDto;
import nl.doyle.mccloud.tourdefrance.web.spring.model.DeelnemerModel;
import nl.doyle.mccloud.tourdefrance.web.spring.model.DeelnemerRennerModel;
import nl.doyle.mccloud.tourdefrance.web.spring.model.RennerModel;

public class DeelnemerRennerCustomConverter implements CustomConverter {
	
	@SuppressWarnings("unchecked")
	public Object convert(Object destination, Object source, Class destClass, Class sourceClass) {
	    //TODO Hier moet nog beter gechecked worden op types. Als types niet kloppen dan moet een Dozer exception gegooid worden.
		if (source == null) {
	      return null;
	    }
	    List<DeelnemerRennerModel> dest = null;
	    
	    //Instance of checking werkt niet met generics. Daarom checken we eerst of de source een lijst
	    //is en later of de waardes in die lijst ook daadwerkelijk van het juiste type is.
	    if (source instanceof DeelnemerWithRennersDto) {
	    	// check to see if the object already exists
	    	
	    	if (destination == null) {
	    		dest = new ArrayList<DeelnemerRennerModel>();
	    	} else {
	    		dest = (ArrayList<DeelnemerRennerModel>) destination;
	    	}
	      for(RennerDto nextRenner: ((DeelnemerWithRennersDto) source).getRenners()) {
	    		DeelnemerRennerModel currentModel = new DeelnemerRennerModel();
	    		DeelnemerModel deelnemer = new DeelnemerModel();
	    		RennerModel renner = new RennerModel();
	    		renner.setNummer(nextRenner.getNummer());
	    		renner.setVoornaam(nextRenner.getVoornaam());
	    		renner.setAchternaam(nextRenner.getAchternaam());
	    		deelnemer.setNummer(((DeelnemerWithRennersDto)source).getNummer());
	    		deelnemer.setVoornaam(((DeelnemerWithRennersDto)source).getVoornaam());
	    		deelnemer.setAchternaam(((DeelnemerWithRennersDto)source).getAchternaam());
	    		deelnemer.setEmail(((DeelnemerWithRennersDto)source).getEmail());
	    		deelnemer.setRekeningnummer(((DeelnemerWithRennersDto)source).getRekeningnummer());
	    		currentModel.setDeelnemer(deelnemer);
	    		currentModel.setRenner(renner);
	    		dest.add(currentModel);
	    	}
	    } else {
	    	throw new MappingException("Converter DeelnemerRennerCustomConverter used incorrectly. Arguments passed in were:"
	          + destination + " and " + source);
	    }
	    return dest;
	}     

}
