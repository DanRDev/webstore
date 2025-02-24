package com.danrdev.webstore.service;

import com.danrdev.webstore.repository.UserRepository;
import com.danrdev.webstore.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(String username, String email, String password) {
        if (userRepository.existsByUsername(username)) {
            throw new RuntimeException("Username is already taken");
        }
        if (userRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("Email is already registered");
        }

        User user = new User(username, email, passwordEncoder.encode(password));

        return userRepository.save(user);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
