package nl.doyle.mccloud.tourdefrance.util;

import java.util.Set;

/**
 * Provides JPA utilities.
 * 
 * @author Duncan Doyle
 * @since 0.3
 */
public class JpaUtil {

	/**
	 * Private constructor to prevent initializtion of this utility class.
	 */
	private JpaUtil() {
	}
	
	/**
	 * Will initialize a JPA lazy loaded {@link Set}
	 * 
	 * @param setToBeInitialized
	 *            the {@link Set} to be initialized.
	 */
	public static void initialize(Set setToBeInitialized) {
		// JPA doesn't have an equivalent of Hibernate initialize. Initializing by calling the 'size()' method on the Set.
		setToBeInitialized.size();
	}

}
