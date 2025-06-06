package com.userSphere.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.userSphere.entities.RoleEntity;

import jakarta.transaction.Transactional;

/**
 * Repository interface for RoleEntity.
 * Provides CRUD operations and custom finder method for role name.
 */
@Repository
public interface RolesRepo extends CrudRepository<RoleEntity, Long> {

    /**
     * Finds a RoleEntity by its role name.
     * 
     * @param roleName the name of the role
     * @return the RoleEntity with the given name, or null if not found
     */
    @Transactional
    RoleEntity findByRoleName(String roleName);
}
