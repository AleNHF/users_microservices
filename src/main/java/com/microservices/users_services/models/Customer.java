package com.microservices.users_services.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "customers")
public class Customer {
    @Id
    private String _id;
    private int id;
    private String name;
    private String phone;
    private String email;
}
