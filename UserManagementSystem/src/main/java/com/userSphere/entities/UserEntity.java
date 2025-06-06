package com.userSphere.entities;

import java.util.Collection;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

/**
 * Represents a system user within the application.
 * Contains personal details, authentication info, and assigned roles.
 * 
 * userId: UUID primary key for unique user identification.
 * 
 * Note: Additional fields like createdAt, lastLogin, updatedAt can be added as needed.
 */
@Entity
@Table(name = "users")
public class UserEntity {

    /**
     * Unique identifier for the user.
     * Using UUID generation strategy for string type ID.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String userId;

    @Column(nullable = false)
    @Size(min = 5, message = "FirstName must be at least 5 characters")
    private String firstName;

    @Column(nullable = false)
    @Size(min = 5, message = "LastName must be at least 5 characters")
    private String lastName;

    /**
     * Unique username chosen by user.
     */
    @Column(nullable = false, unique = true)
    private String userName;

    /**
     * Unique email of the user.
     */
    @Column(nullable = false, unique = true)
    private String mailId;

    /**
     * Contact number stored as a Long.
     * Consider validation for length (10 digits) elsewhere.
     */
    @Column(nullable = false)
    private Long contactNumber;

    /**
     * Encrypted password, stored as a string.
     * Password size validation applies on the plaintext password before encryption.
     */
    @Column(nullable = false)
    @Size(min = 8, message = "Password must be at least 8 characters length")
    private String encryptedPassword;

    /**
     * Many-to-many relationship with roles.
     * CascadeType.PERSIST allows persisting new roles with user creation.
     * FetchType.EAGER loads roles immediately with user.
     */
    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(
        name = "users_roles",
        joinColumns = @JoinColumn(name = "users_id", referencedColumnName = "userId"),
        inverseJoinColumns = @JoinColumn(name = "roles_id", referencedColumnName = "id")
    )
    private Collection<RoleEntity> roles;

    // Default constructor required by JPA
    public UserEntity() {
        super();
    }

    // Parameterized constructor for easy initialization
    public UserEntity(String userId,
                      @Size(min = 5, message = "FirstName must be at least 5 characters") String firstName,
                      @Size(min = 5, message = "LastName must be at least 5 characters") String lastName,
                      String userName,
                      String mailId,
                      Long contactNumber,
                      @Size(min = 8, message = "Password must be at least 8 characters length") String encryptedPassword,
                      Collection<RoleEntity> roles) {
        super();
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.mailId = mailId;
        this.contactNumber = contactNumber;
        this.encryptedPassword = encryptedPassword;
        this.roles = roles;
    }

    // Getters and setters
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
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
    public String getEncryptedPassword() {
        return encryptedPassword;
    }
    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }
    public Collection<RoleEntity> getRoles() {
        return roles;
    }
    public void setRoles(Collection<RoleEntity> roles) {
        this.roles = roles;
    }

}
