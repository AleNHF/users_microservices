package com.microservices.users_services.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.microservices.users_services.models.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    User findByUsername(String username); 
}
