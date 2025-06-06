package com.userSphere.exceptionHandler;

import java.util.Date;

/**
 * ErrorMessage class represents the structure of an error response 
 * that will be sent back to clients when an exception occurs.
 * 
 * It contains the timestamp of when the error occurred and a message
 * describing the error.
 */
public class ErrorMessage {
	
	/**
	 * The date and time when the error occurred.
	 */
	private Date errorOccuredDate;
	
	/**
	 * A human-readable message describing the error.
	 */
	private String message;
	
	/**
	 * Default no-argument constructor.
	 * Required for frameworks that use reflection to instantiate objects.
	 */
	public ErrorMessage() {
		super();
	}
	
	/**
	 * Parameterized constructor to create an ErrorMessage with 
	 * specific timestamp and message.
	 * 
	 * @param errorOccuredDate The date and time when the error occurred.
	 * @param message The error message to be conveyed.
	 */
	public ErrorMessage(Date errorOccuredDate, String message) {
		super();
		this.errorOccuredDate = errorOccuredDate;
		this.message = message;
	}
	
	/**
	 * Getter for errorOccuredDate.
	 * 
	 * @return the date and time when the error occurred.
	 */
	public Date getErrorOccuredDate() {
		return errorOccuredDate;
	}
	
	/**
	 * Setter for errorOccuredDate.
	 * 
	 * @param errorOccuredDate the date and time to set for the error occurrence.
	 */
	public void setErrorOccuredDate(Date errorOccuredDate) {
		this.errorOccuredDate = errorOccuredDate;
	}
	
	/**
	 * Getter for the error message.
	 * 
	 * @return the message describing the error.
	 */
	public String getMessage() {
		return message;
	}
	
	/**
	 * Setter for the error message.
	 * 
	 * @param message the error message to set.
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
}
