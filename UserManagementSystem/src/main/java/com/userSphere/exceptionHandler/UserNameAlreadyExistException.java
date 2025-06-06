package com.userSphere.exceptionHandler;

/**
 * Custom exception thrown when a username already exists 
 * in the system during user registration or update.
 * 
 * This exception extends RuntimeException for unchecked exception handling.
 */
public class UserNameAlreadyExistException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor to initialize the exception with a specific error message.
	 * 
	 * @param message The detailed error message explaining the cause of exception.
	 */
	public UserNameAlreadyExistException(String message) {
		super(message);
	}
}
