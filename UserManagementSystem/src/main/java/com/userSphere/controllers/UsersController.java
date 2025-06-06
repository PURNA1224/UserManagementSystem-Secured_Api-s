package com.userSphere.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.userSphere.pojos.CreateUserRequestModel;
import com.userSphere.pojos.CreateUserResponseModel;
import com.userSphere.pojos.UserDto;
import com.userSphere.pojos.UserResponseModel;
import com.userSphere.services.UsersService;
import com.userSphere.shared.Roles;

import jakarta.validation.Valid;

@RestController
public class UsersController {
	
	@Autowired
	UsersService userService;
	
	@Autowired
	ModelMapper mapper;
	
	/**
     * Registers a new user.
     * Only the default "USER" role is assigned at registration.
     * @param requestModel contains user input data.
     * @return newly created user response.
     */
	@PostMapping("/register")
	public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserRequestModel requestModel) {
		
		// Convert the incoming request model to a DTO for service layer processing.
		UserDto userDto = mapper.map(requestModel, UserDto.class);
		
		// Assign default role as USER for newly registering users.
		userDto.setRoles(Arrays.asList(Roles.ROLE_USER.toString()));
		
		 // Call service method to create a new user and get back the created DTO.
		UserDto createdUserDto = userService.createUser(userDto, requestModel.getPassword());
		
		 // Map the created DTO to a response model for sending back to client.
		CreateUserResponseModel response = mapper.map(createdUserDto, CreateUserResponseModel.class);
		
		//Returning the response entity with status "ok" and response as body.
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(response);
	}
	
	/**
     * Fetches the list of all users.
     * Only accessible by users with ADMIN role.
     * @return list of user details.
     */
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/users")
	public ResponseEntity<?> getAllUsers(){
		
		// Fetch list of all users.
		List<UserDto> userDto = userService.getAllUsers();
		
		// Prepare response list by mapping DTOs to response models.
		List<CreateUserResponseModel> response = new ArrayList<>();
		
		// Mapping every element in the userDto to the "CreateUserResponseModel".
		userDto.forEach(user->{
			response.add(mapper.map(user, CreateUserResponseModel.class));
		});
		
		// Returning the response entity with status "ok" and response as body.
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(response);
	}
	
	/**
     * Fetches a specific user's details by ID.
     * Accessible by USER and ADMIN roles.
     * @param userId the unique ID of the user.
     * @return user details.
     */
	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	@GetMapping("/users/{id}")
	public ResponseEntity<?> getUserById(@PathVariable("id") String userId){
		
		// Retrieve the user DTO using the ID.
		UserDto userDto = userService.getUserById(userId);
		
		 // Convert the DTO to a response model.
		UserResponseModel response = mapper.map(userDto, UserResponseModel.class);
		
		//Returning the response entity with status "ok" and response as body.
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(response);
	}
	
	/**
     * Deletes a user by their ID.
     * Only accessible by ADMIN role.
     * @param userId the unique ID of the user.
     * @return confirmation message.
     */
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("users/{id}")
	public ResponseEntity<?> deleteUserById(@PathVariable("id") String userId){
		
		// Invoke the service layer to delete the user by ID.
		userService.deleteById(userId);
		
		//Returning the response entity with status "ok" and "User deleted" as body.
		return ResponseEntity.ok().body("User deleted");
	}
	
}
