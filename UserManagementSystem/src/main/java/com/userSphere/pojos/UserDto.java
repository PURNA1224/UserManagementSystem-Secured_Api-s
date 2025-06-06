package com.userSphere.pojos;

import java.io.Serializable;
import java.util.Collection;

/**
 * UserDto is a Data Transfer Object used to carry user data 
 * between processes or layers within the application.
 * 
 * It implements Serializable to allow easy serialization
 * and deserialization of user data.
 */
public class UserDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
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
	 * User's unique username.
	 */
	private String userName;
	
	/**
	 * User's contact number.
	 */
	private Long contactNumber;
	
	/**
	 * User's encrypted password.
	 */
	private String encryptedPassword;
	
	/**
	 * Roles assigned to the user represented as a collection of role names.
	 */
	private Collection<String> roles;

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

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	public Collection<String> getRoles() {
		return roles;
	}

	public void setRoles(Collection<String> roles) {
		this.roles = roles;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
