package com.userSphere.serviceImplementations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.userSphere.entities.RoleEntity;
import com.userSphere.entities.UserEntity;
import com.userSphere.exceptionHandler.MailAlreadyExistException;
import com.userSphere.exceptionHandler.UserNameAlreadyExistException;
import com.userSphere.exceptionHandler.UserNotFoundException;
import com.userSphere.pojos.UserDto;
import com.userSphere.repositories.RolesRepo;
import com.userSphere.repositories.UsersRepo;
import com.userSphere.security.UsersPrincipal;
import com.userSphere.services.UsersService;

import jakarta.transaction.Transactional;

@Service
public class UsersServiceImpl implements UsersService{
	
	@Autowired
	UsersRepo usersRepo;  // Repository to perform UserEntity DB operations
	
	@Autowired
	ModelMapper mapper;  // Mapper to convert between entities and DTOs
	
	@Autowired
	private RolesRepo rolesRepository;  // Repository to fetch RoleEntity objects
	
	BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();  // Password encoder for hashing passwords

	@Override
	public UserDetails loadUserByUsername(String mailId) throws UsernameNotFoundException {
		// Load user by email for Spring Security authentication
		UserEntity userEntity = usersRepo.findByMailId(mailId.toLowerCase());
		if(userEntity == null) throw new UsernameNotFoundException("User with email "+mailId+ " doesn't exist.");
		return new UsersPrincipal(userEntity);
	}
	
	@Override
	@Transactional
	public UserDto createUser(UserDto userDto, String password) throws UserNameAlreadyExistException, MailAlreadyExistException {

	    // Check if email already exists in the DB
	    UserEntity userMailId = usersRepo.findByMailId(userDto.getMailId());
	    // Check if username already exists in the DB
	    UserEntity userName = usersRepo.findByUserName(userDto.getUserName());

	    if (userMailId != null) {
	        throw new MailAlreadyExistException("User with mail ID " + userMailId.getMailId() + " already exists.");
	    }

	    if (userName != null) {
	        throw new UserNameAlreadyExistException("Username " + userName.getUserName() + " already exists.");
	    }

	    // Encrypt the plain password before saving
	    userDto.setEncryptedPassword(bcrypt.encode(password));
	    // Normalize email to lowercase
	    userDto.setMailId(userDto.getMailId().toLowerCase());

	    // Fetch RoleEntity objects for each role string and add to collection
	    Collection<RoleEntity> userRoleEntity = new HashSet<>();
	    for (String role : userDto.getRoles()) {
	        RoleEntity roleEntity = rolesRepository.findByRoleName(role);
	        if (roleEntity != null) {
	            userRoleEntity.add(roleEntity);
	        }
	    }

	    // Map UserDto to UserEntity and assign roles
	    UserEntity user = mapper.map(userDto, UserEntity.class);
	    user.setRoles(userRoleEntity);
	    // Persist the new user entity
	    usersRepo.save(user);

	    return userDto;  // Return the created user DTO
	}


	@Override
	public List<UserDto> getAllUsers() {
		// Retrieve all users from DB
		List<UserEntity> users =  (List<UserEntity>) usersRepo.findAll();
		
		List<UserDto> userDto = new ArrayList<>();
		// Map each UserEntity to UserDto and collect into list
		users.forEach(user->{
			userDto.add(mapper.map(user, UserDto.class));
		});
		return userDto;
	}

	@Override
	public UserDto getUserById(String userId) {
		// Find user entity by userId
		UserEntity user = usersRepo.findByUserId(userId);
		if(user == null) throw new UserNotFoundException("User with user id "+userId+" doesn't exist");
		// Map found user entity to DTO
		UserDto userDto = mapper.map(user, UserDto.class);
		return userDto;
	}

	@Override
	@Transactional
	public void deleteById(String userId) {
		// Check if user exists before deletion
		UserEntity user = usersRepo.findByUserId(userId);
		if(user == null) throw new UserNotFoundException("User with user id "+userId+" doesn't exist");
		// Delete user by userId
		usersRepo.deleteByUserId(userId);
	}

}
