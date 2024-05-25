package com.microservices.users_services.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
