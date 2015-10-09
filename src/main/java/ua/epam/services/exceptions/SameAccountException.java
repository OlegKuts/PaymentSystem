package ua.epam.services.exceptions;

public class SameAccountException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SameAccountException(String message) {
		super(message);
	}
}
