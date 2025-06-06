package com.userSphere;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.userSphere.entities.AuthorityEntity;
import com.userSphere.entities.RoleEntity;
import com.userSphere.entities.UserEntity;
import com.userSphere.repositories.AuthoritiesRepo;
import com.userSphere.repositories.RolesRepo;
import com.userSphere.repositories.UsersRepo;
import com.userSphere.shared.Authorities;
import com.userSphere.shared.Roles;

import jakarta.transaction.Transactional;



/**
 * This component class is responsible for initializing essential data when the application starts.
 * It ensures necessary authorities, roles, and a default admin user are created in the database.
 */

@Component
public class InitialSetup {
	
	@Autowired
	AuthoritiesRepo authRepo;
	
	@Autowired
	RolesRepo rolesRepo;
	
	@Autowired
	UsersRepo usersRepo;
	
	// Password encoder to encrypt the admin's password
	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	/**
     * This method runs automatically after the Spring Boot application is fully initialized.
     * It checks for and inserts default authorities, roles, and an admin user if not already present.
     */
	@EventListener
	@Transactional
	public void initialSetup(ApplicationReadyEvent applicationReadyEvent) {
		
		// Create authorities if not exist
		AuthorityEntity read_auth = createAuth(Authorities.READ_AUTHORITY.toString());
		AuthorityEntity write_auth = createAuth(Authorities.WRITE_AUTHORITY.toString());
		AuthorityEntity delete_auth = createAuth(Authorities.DELETE_AUTHORITY.toString());
		
		// Create roles and assign authorities
		createRole(Roles.ROLE_USER.toString(), Arrays.asList(read_auth, write_auth));
		RoleEntity adminRoleEntity = createRole(Roles.ROLE_ADMIN.toString(), Arrays.asList(read_auth, write_auth, delete_auth));
		
		// Create default admin user with admin role
		UserEntity adminEntity = new UserEntity();
		adminEntity.setUserName("PURNA1224");
		adminEntity.setFirstName("Purna");
		adminEntity.setLastName("Kandiboina");
		adminEntity.setMailId("purna@userSphere.com".toLowerCase());
		adminEntity.setContactNumber(7995288182l);
		adminEntity.setEncryptedPassword(passwordEncoder.encode("Purna@1234"));
		adminEntity.setRoles(Arrays.asList(adminRoleEntity));
		
		
		/*
		 * Check if a user with the specified email already exists in the database.
		 * If not found, save the newly created admin user to prevent duplicate entries.
		 */
		UserEntity presentEntity = usersRepo.findByMailId("purna@userSphere.com".toLowerCase());
		if(presentEntity == null)
			usersRepo.save(adminEntity);
	}
	
	/**
     * Creates an authority if it doesn't exist in the database.
     *
     * @param authName The name of the authority.
     * @return The created or existing AuthorityEntity.
     */
	@Transactional
	private AuthorityEntity createAuth(String authName) {
		AuthorityEntity authEntity = authRepo.findByAuthName(authName);
		if(authEntity == null) {
			authEntity = new AuthorityEntity();
			authEntity.setAuthName(authName);
		}
		return authEntity;
	}
	
	 /**
     * Creates a role with associated authorities if it doesn't exist.
     *
     * @param roleName The name of the role.
     * @param auths    A collection of authorities to assign to the role.
     * @return The created or existing RoleEntity.
     */
	@Transactional
	private RoleEntity createRole(String roleName, Collection<AuthorityEntity> auths) {
		RoleEntity roleEntity = rolesRepo.findByRoleName(roleName);
		if(roleEntity == null) {
			roleEntity = new RoleEntity();
			roleEntity.setRoleName(roleName);
			roleEntity.setAuthorities(auths);
		}
		return roleEntity;
	}
}
