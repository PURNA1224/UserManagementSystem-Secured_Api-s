package com.userSphere.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.userSphere.entities.AuthorityEntity;

import jakarta.transaction.Transactional;

/**
 * Repository interface for AuthorityEntity.
 * Provides CRUD operations and custom finder method for authority name.
 */
@Repository
public interface AuthoritiesRepo extends CrudRepository<AuthorityEntity, Long> {

    /**
     * Finds an AuthorityEntity by its authority name.
     * 
     * @param authName the name of the authority
     * @return the AuthorityEntity with the given name, or null if not found
     */
    @Transactional
    AuthorityEntity findByAuthName(String authName);
}
