package com.userSphere.security;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.userSphere.entities.AuthorityEntity;
import com.userSphere.entities.RoleEntity;
import com.userSphere.entities.UserEntity;

/**
 * UsersPrincipal implements Spring Security's UserDetails interface
 * and adapts the application's UserEntity to the framework's authentication model.
 */
public class UsersPrincipal implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	
	private UserEntity userEntity;
	
	/**
	 * Constructs UsersPrincipal with a given UserEntity.
	 * 
	 * @param userEntity the user entity from the database
	 */
	public UsersPrincipal(UserEntity userEntity){
		this.userEntity = userEntity;
	}

	/**
	 * Returns the authorities granted to the user, including roles and permissions.
	 * 
	 * @return collection of granted authorities (roles and authorities)
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> grantedAuthorities = new HashSet<>();
		Collection<AuthorityEntity> authorities = new HashSet<>();
		
		Collection<RoleEntity> roles = userEntity.getRoles();
		
		// If user has no roles, return empty authorities
		if (roles == null) return grantedAuthorities;
		
		// Add role names as authorities and collect all authorities from roles
		roles.forEach(role -> {
			grantedAuthorities.add(new SimpleGrantedAuthority(role.getRoleName()));
			authorities.addAll(role.getAuthorities());
		});
		
		// Add individual authorities
		authorities.forEach(auth -> {
			grantedAuthorities.add(new SimpleGrantedAuthority(auth.getAuthName()));
		});
		
		return grantedAuthorities;
	}

	/**
	 * Returns the user's encrypted password.
	 */
	@Override
	public String getPassword() {
		return userEntity.getEncryptedPassword();
	}

	/**
	 * Returns the username used to authenticate the user.
	 */
	@Override
	public String getUsername() {
		return userEntity.getUserName();
	}
	
	/**
	 * Indicates whether the user's account has expired. 
	 * 
	 * @return true, indicating the account is not expired
	 */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	/**
	 * Indicates whether the user is locked or unlocked.
	 * 
	 * @return true, indicating the account is not locked
	 */
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	/**
	 * Indicates whether the user's credentials (password) has expired.
	 * 
	 * @return true, indicating the credentials are valid (not expired)
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/**
	 * Indicates whether the user is enabled or disabled.
	 * 
	 * By default, returns true; can be customized if UserEntity has such a property.
	 */
	@Override
	public boolean isEnabled() {
		return true;
	}
}
