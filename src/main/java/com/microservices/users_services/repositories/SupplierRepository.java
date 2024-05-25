package com.microservices.users_services.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.microservices.users_services.models.Supplier;

@Repository
public interface SupplierRepository extends MongoRepository<Supplier, String> {
    
}