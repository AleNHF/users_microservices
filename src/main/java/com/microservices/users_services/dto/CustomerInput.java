package com.microservices.users_services.dto;

import lombok.Data;

@Data
public class CustomerInput {
    private String name;
    private String phone;
    private String email;
}
