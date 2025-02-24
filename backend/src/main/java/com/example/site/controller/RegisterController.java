package com.example.site.controller;

import com.example.site.auth.request.RegisterRequest;
import com.example.site.auth.response.RegisterResponse;
import com.example.site.model.User;
import com.example.site.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/register")
public class RegisterController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping
    public RegisterResponse registerUser(@RequestBody RegisterRequest registerRequest) {
        // Check if the user already exists
        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            return new RegisterResponse(false, "Username is already taken");
        }

        // Create a new user and encode the password
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setEmail(registerRequest.getEmail());

        // Save the user to the database
        userRepository.save(user);

        return new RegisterResponse(true, "User registered successfully");
    }
}
