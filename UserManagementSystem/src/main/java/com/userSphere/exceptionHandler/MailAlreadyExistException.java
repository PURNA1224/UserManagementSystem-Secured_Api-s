package com.userSphere.exceptionHandler;

/**
 * Custom exception to be thrown when a mail (email) already exists 
 * in the system during user registration or update.
 * 
 * Extends RuntimeException to allow unchecked exception handling.
 */
public class MailAlreadyExistException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor to create the exception with a specific error message.
	 * 
	 * @param message the detail message explaining the exception.
	 */
	public MailAlreadyExistException(String message) {
		super(message);
	}
}
