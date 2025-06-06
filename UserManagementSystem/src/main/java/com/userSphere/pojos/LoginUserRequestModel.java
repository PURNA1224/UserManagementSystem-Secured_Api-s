package com.userSphere.pojos;

/**
 * LoginUserRequestModel represents the data sent by a user
 * when attempting to log in to the system.
 * 
 * It contains the user's email (mailId) and password.
 */
public class LoginUserRequestModel {
	
	/**
	 * The email address of the user trying to log in.
	 */
	private String mailId;
	
	/**
	 * The password provided by the user for authentication.
	 */
	private String password;
	
	/**
	 * Default no-argument constructor.
	 */
	public LoginUserRequestModel() {
		super();
	}
	
	/**
	 * Parameterized constructor to initialize the login request model
	 * with email and password.
	 * 
	 * @param mailId The email address of the user.
	 * @param password The password of the user.
	 */
	public LoginUserRequestModel(String mailId, String password) {
		super();
		this.mailId = mailId;
		this.password = password;
	}

	// Getters and setters
	
	public String getMailId() {
		return mailId;
	}

	public void setMailId(String mailId) {
		this.mailId = mailId;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
