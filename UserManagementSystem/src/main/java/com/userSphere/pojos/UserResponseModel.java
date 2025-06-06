package com.userSphere.pojos;

/**
 * UserResponseModel represents the user data sent as a response
 * to the client after successful user-related operations.
 */
public class UserResponseModel {

    /**
     * User's first name.
     */
    private String firstName;

    /**
     * User's last name.
     */
    private String lastName;

    /**
     * User's unique username.
     */
    private String userName;

    /**
     * User's email address.
     */
    private String mailId;

    /**
     * User's contact number.
     */
    private Long contactNumber;

    /**
     * Default no-argument constructor.
     */
    public UserResponseModel() {
        super();
    }

    /**
     * Parameterized constructor to create a UserResponseModel with all fields.
     * 
     * @param firstName User's first name
     * @param lastName User's last name
     * @param userName User's username
     * @param mailId User's email
     * @param contactNumber User's contact number
     */
    public UserResponseModel(String firstName, String lastName, String userName, String mailId, Long contactNumber) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.mailId = mailId;
        this.contactNumber = contactNumber;
    }

    // Getters and Setters

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMailId() {
        return mailId;
    }

    public void setMailId(String mailId) {
        this.mailId = mailId;
    }

    public Long getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(Long contactNumber) {
        this.contactNumber = contactNumber;
    }

}
