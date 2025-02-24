package com.danrdev.webstore.controller;

import com.danrdev.webstore.repository.UserRepository;
import com.danrdev.webstore.auth.request.RegisterRequest;
import com.danrdev.webstore.auth.response.RegisterResponse;
import com.danrdev.webstore.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class RegisterController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/register")
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
