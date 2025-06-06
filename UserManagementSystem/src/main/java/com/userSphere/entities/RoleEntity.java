package com.userSphere.entities;

import java.util.Collection;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

/**
 * Entity representing a role in the system.
 * Each role (e.g., ROLE_ADMIN, ROLE_USER) can have multiple authorities/permissions.
 */
@Entity
@Table(name = "roles")
public class RoleEntity {

    // Primary key for the roles table, auto-generated.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Name of the role (e.g., ROLE_USER, ROLE_ADMIN).
    private String roleName;

    // Many-to-Many relationship with UserEntity.
    // This is the inverse side of the mapping; the owning side is in UserEntity.
    @ManyToMany(mappedBy = "roles")
    private Collection<UserEntity> users;

    /**
     * Many-to-Many relationship with AuthorityEntity.
     * A role can have multiple authorities, and each authority can belong to multiple roles.
     * - CascadeType.PERSIST: New authorities can be persisted when saving a role.
     * - FetchType.EAGER: Authorities are loaded immediately when a role is fetched.
     * - JoinTable: Defines the join table 'roles_authorities' and foreign keys.
     */
    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(
        name = "roles_authorities",
        joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "auth_id", referencedColumnName = "id")
    )
    private Collection<AuthorityEntity> authorities;

    // Default constructor required by JPA
    public RoleEntity() {
        super();
    }

    // Parameterized constructor for easy creation
    public RoleEntity(Long id, String roleName) {
        super();
        this.id = id;
        this.roleName = roleName;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Collection<AuthorityEntity> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<AuthorityEntity> authorities) {
        this.authorities = authorities;
    }

}
