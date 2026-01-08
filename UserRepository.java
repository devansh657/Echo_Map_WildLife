package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.User;

@Repository 
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmailAddress(String email_address);
    Optional<User> findByUsernameAndPassword(String username, String password);
    Optional<User> findByPassword(String password);
    Optional<User> findByBio(String bio);
    
}
