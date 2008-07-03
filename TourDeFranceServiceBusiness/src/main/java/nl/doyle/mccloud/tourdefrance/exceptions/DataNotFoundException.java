package nl.doyle.mccloud.tourdefrance.exceptions;

public class DataNotFoundException extends Exception {

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor.
	 * 
	 * @param message
	 *            the exception message
	 */
	public DataNotFoundException(String message) {
		super(message);
	}

}
