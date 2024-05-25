package com.microservices.users_services.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.microservices.users_services.models.Customer;

public interface CustomerRepository extends MongoRepository<Customer, String> {
    
}
