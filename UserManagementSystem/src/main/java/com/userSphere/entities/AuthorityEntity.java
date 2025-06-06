package com.userSphere.entities;

import java.util.Collection;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

/**
 * Entity representing a specific authority/permission in the system.
 * For example: READ_AUTHORITY, WRITE_AUTHORITY, DELETE_AUTHORITY, etc.
 */
@Entity
@Table(name = "authorities")
public class AuthorityEntity {

    // Primary key for the authority table, auto-generated.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Name of the authority/permission (must be unique in business logic).
    private String authName;

    // Many-to-Many relationship with RoleEntity.
    // This field is mapped by the "authorities" field in RoleEntity.
    @ManyToMany(mappedBy = "authorities")
    private Collection<RoleEntity> roles;

    // Default constructor required by JPA.
    public AuthorityEntity() {
        super();
    }

    // Parameterized constructor for convenience.
    public AuthorityEntity(Long id, String authName) {
        super();
        this.id = id;
        this.authName = authName;
    }

    // Getters and setters.
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthName() {
        return authName;
    }

    public void setAuthName(String authName) {
        this.authName = authName;
    }

}
