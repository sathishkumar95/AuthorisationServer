package com.stackz.repository;

import com.stackz.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends MongoRepository<Role, String> {

    @Query(value = "{ 'username' : ?0 }")
    Role findByUsername(String username);

}
