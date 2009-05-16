package nl.doyle.mccloud.tourdefrance.web.utils.compare;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Comparator;
import java.util.Locale;

/**
 * 
 * Comparator for Strings which contain amounts. They therefore need to be compared as doubles.
 * 
 * @author Duncan Doyle
 * @since 0.1
 */
public final class BedragStringComparator implements Comparator<String> {

	/**
	 * Compares the passed {@link String Strings} as doubles.. Values are sorted in reverse order so the highest value will be on top.
	 * 
	 * @param o1
	 *            the first {@link String} to be used in the comparison
	 * @param o2
	 *            the second {@link String} to be used in the comparison
	 * 
	 * @return +1 when o1 < 02, -1 when o1 > 02
	 */
	public int compare(final String o1, final String o2) {
		int comparison = 0;

		// Het zijn Strings met bedragen, dus sorteren als doubles
		Locale dutch = new Locale("nl", "NL");
		NumberFormat format = NumberFormat.getInstance(dutch);
		double firstBedrag;
		double secondBedrag;
		try {
			firstBedrag = format.parse((String) o1).doubleValue();
			secondBedrag = format.parse((String) o2).doubleValue();
		} catch (ParseException pe) {
			throw new RuntimeException(pe);
		}
		// We sorteren andersom, zodat het hoogste bedrag bovenaan staat.
		if (firstBedrag < secondBedrag) {
			comparison = 1;
		} else if (firstBedrag > secondBedrag) {
			comparison = -1;
		}
		return comparison;
	}

}
