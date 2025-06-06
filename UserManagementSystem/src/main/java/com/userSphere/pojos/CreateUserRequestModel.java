package com.userSphere.pojos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * POJO class representing the data required to create a new user.
 * 
 * This model is used to receive and validate user input data when
 * creating a user account.
 */
public class CreateUserRequestModel {
	
	/**
	 * User's first name; cannot be null and must have a minimum length of 2.
	 */
	@NotNull(message="First Name can't be null")
	@Size(min = 2, message="enter minimum 2 letters for firstName")
	private String firstName;
	
	/**
	 * User's last name; cannot be null and must have a minimum length of 2.
	 */
	@NotNull(message="Last Name can't be null")
	@Size(min = 2, message="enter minimum 2 letters for lastName")
	private String lastName;
	
	/**
	 * User's email address; cannot be null and must be a valid email format.
	 */
	@NotNull(message="Email can't be null")
	@Email
	private String mailId;
	
	/**
	 * User's chosen username.
	 */
	private String userName;
	
	/**
	 * User's contact phone number.
	 */
	private Long contactNumber;
	
	/**
	 * User's password; must be at least 8 characters long.
	 */
	@Size(min = 8, message = "Password must be 8 characters length")
	private String password;
	
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
