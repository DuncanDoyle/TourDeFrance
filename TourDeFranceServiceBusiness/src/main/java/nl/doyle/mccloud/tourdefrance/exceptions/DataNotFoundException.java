package nl.doyle.mccloud.tourdefrance.exceptions;

public final class DataNotFoundException extends Exception {

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
	public DataNotFoundException(final String message) {
		super(message);
	}

}
