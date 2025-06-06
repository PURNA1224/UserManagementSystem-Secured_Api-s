package com.userSphere.exceptionHandler;

/**
 * Custom exception thrown when a user is not found in the system.
 * 
 * This exception extends RuntimeException and is used to indicate that
 * a requested user entity does not exist.
 */
public class UserNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor to create the exception with a specific error message.
	 * 
	 * @param message Detailed message describing the reason for the exception.
	 */
	public UserNotFoundException(String message) {
		super(message);
	}
}
