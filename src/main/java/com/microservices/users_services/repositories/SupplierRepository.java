package com.microservices.users_services.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.microservices.users_services.models.Supplier;

public interface SupplierRepository extends MongoRepository<Supplier, String> {
    
}