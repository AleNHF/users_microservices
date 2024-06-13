package com.microservices.users_services.services;

import java.util.List;
import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.microservices.users_services.config.JwtTokenProvider;
import com.microservices.users_services.models.CustomUserDetails;
import com.microservices.users_services.models.LoginDto;
import com.microservices.users_services.models.User;
import com.microservices.users_services.repositories.UserRepository;

@Service
public class UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private JwtTokenProvider jwtTokenProvider;
    private AuthenticationManager authenticationManager;

    private int nextCustomId = 16;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
    }

    public User createUser(User user) {
        user.setId(getNextCustomId());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(int id) {
        return userRepository.getById(id);
    }

    public Boolean deleteUser(String id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        } else {
            throw new IllegalArgumentException("User not found with id: " + id);
        }
    }

    public User updateUser(String id, User updatedUser) {
        return userRepository.findById(id).map(user -> {
            if (updatedUser.getUsername() != null) {
                user.setUsername(updatedUser.getUsername());
            }

            if (updatedUser.getEmail() != null) {
                user.setEmail(updatedUser.getEmail());
            }

            if (updatedUser.getPassword() != null) {
                user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
            }
            //user.setRoles(updatedUser.getRoles());
            return userRepository.save(user);
        }).orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));
    }

    public LoginDto loginUser(String username, String password) {
        // Authenticate the user
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(username, password)
        );
    
        // Set the authentication context
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        // Retrieve the user details
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        
        // Safely cast to CustomUserDetails
        if (userDetails instanceof CustomUserDetails) {
            CustomUserDetails customUserDetails = (CustomUserDetails) userDetails;
            
            // Generate the token
            String token = jwtTokenProvider.generateToken(customUserDetails);
            
            // Create and return the response DTO
            return new LoginDto(
                token,
                customUserDetails.getId(),
                customUserDetails.getUsername(),
                customUserDetails.getEmail(),
                customUserDetails.getRole()
            );
        } else {
            // Handle the case where the userDetails is not an instance of CustomUserDetails
            throw new IllegalStateException("Unexpected user details type: " + userDetails.getClass().getName());
        }
    }    

    /* public String loginUser(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return jwtTokenProvider.generateToken(userDetails);
    } */
    
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    private synchronized int getNextCustomId() {
        return nextCustomId++;
    }
}
