package com.microservices.users_services.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.microservices.users_services.models.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> { 
    User findByUsername(String username); 
    User findByEmail(String email); 
    Optional<User> getById(int id);
}
