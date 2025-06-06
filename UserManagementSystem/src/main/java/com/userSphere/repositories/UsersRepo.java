package com.userSphere.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.userSphere.entities.UserEntity;

/**
 * Repository interface for UserEntity.
 * Provides CRUD operations and custom finder methods for users.
 */
@Repository
public interface UsersRepo extends CrudRepository<UserEntity, String> {
	
	/**
	 * Fetches a user based on their username.
	 * 
	 * @param userName the username to search for.
	 * @return the UserEntity matching the username.
	 */
	UserEntity findByUserName(String userName);
	
	/**
	 * Fetches a user using their unique userId.
	 * 
	 * @param userId the unique user ID.
	 * @return the UserEntity matching the userId.
	 */
	UserEntity findByUserId(String userId);
	
	/**
	 * Deletes a user from the database using their userId.
	 * 
	 * @param userId the unique user ID.
	 */
	void deleteByUserId(String userId);
	
	/**
	 * Fetches a user based on their email (mailId).
	 * 
	 * @param mailId the email address to search for.
	 * @return the UserEntity matching the mailId.
	 */
	UserEntity findByMailId(String mailId);
}
