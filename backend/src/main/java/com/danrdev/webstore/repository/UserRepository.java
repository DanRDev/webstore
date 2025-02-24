package com.danrdev.webstore.repository;

import com.danrdev.webstore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    boolean existsByUsername(String username);

    Optional<User> findByUsername(String username);
}
