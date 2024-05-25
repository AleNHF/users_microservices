package com.microservices.users_services.dto;

import lombok.Data;

@Data
public class UserInput {
    private String username;
    private String email;
    private String password;
}
