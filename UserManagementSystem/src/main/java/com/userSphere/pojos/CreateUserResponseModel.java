package com.userSphere.pojos;

/**
 * Response model representing the user data sent back after a successful user creation.
 * 
 * This POJO contains user information excluding sensitive data like password.
 */
public class CreateUserResponseModel {

	/**
	 * User's first name.
	 */
	private String firstName;

	/**
	 * User's last name.
	 */
	private String lastName;

	/**
	 * User's email address.
	 */
	private String mailId;
	
	/**
	 * User's username.
	 */
	private String userName;
	
	/**
	 * User's contact phone number.
	 */
	private Long contactNumber;

	// Getters and setters
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMailId() {
		return mailId;
	}

	public void setMailId(String mailId) {
		this.mailId = mailId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(Long contactNumber) {
		this.contactNumber = contactNumber;
	}
	
}
