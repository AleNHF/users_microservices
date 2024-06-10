package com.microservices.users_services.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "users")
public class User {
    @Id
    private String _id;    
    private int id; 
    private String username; 
    private String email;
    private String password;
    //private Set<RoleEnum> roles = new HashSet<>();  
}
