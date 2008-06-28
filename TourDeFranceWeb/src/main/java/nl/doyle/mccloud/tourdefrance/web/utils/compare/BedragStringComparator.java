package nl.doyle.mccloud.tourdefrance.web.utils.compare;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Comparator;
import java.util.Locale;

public class BedragStringComparator implements Comparator<String> {

	public int compare(String o1, String o2) {
		int comparison = 0;
		
		// Het zijn Strings met bedragen, dus sorteren als doubles
		Locale dutch = new Locale("nl", "NL");
    	NumberFormat format = NumberFormat.getInstance(dutch); 
    	double firstBedrag;
    	double secondBedrag;
    	try {
    		firstBedrag = format.parse((String) o1).doubleValue();
    		secondBedrag = format.parse((String)o2).doubleValue();
    	} catch (ParseException pe) {
    		throw new RuntimeException(pe);
    	}
		//We sorteren andersom, zodat het hoogste bedrag bovenaan staat.
		if (firstBedrag < secondBedrag) {
			comparison = 1;
		} else if (firstBedrag > secondBedrag) {
			comparison = -1;
		} 
		return comparison;
	}

}
