package com.userSphere.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.userSphere.pojos.UserDto;

// Service interface for User related operations.
// Extends UserDetailsService to integrate with Spring Security for authentication.
public interface UsersService extends UserDetailsService {

	// Creates a new user with given UserDto and plain password.
	// Returns the created UserDto.
	UserDto createUser(UserDto userDto, String password);

	// Retrieves a list of all users as UserDto objects.
	List<UserDto> getAllUsers();

	// Retrieves a single user by their unique userId.
	// Throws exception if user not found.
	UserDto getUserById(String userId);

	// Deletes a user identified by their userId.
	void deleteById(String userId);
}
