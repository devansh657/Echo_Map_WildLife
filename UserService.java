package com.example.demo.service;
import java.util.Optional;
import java.util.List;
import java.util.Collections;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service; // Import the Service annotation

import com.example.demo.model.Rewards;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service // Annotate the class with @Service
public class UserService {

@Autowired
    //Using the required repository
    private final UserRepository userRepository;

    
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<Rewards> findRewardsByUsername(String username) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return user.getRewardsReceived(); 
        }
        return Collections.emptyList();
    }

    public int findPointsByUsername(String username) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return user.getPointsEarned(); // Assuming the points earned by the user are stored in a field named "pointsEarned" in the User entity
        } else {
            return 0; // Return 0 if the user with the given username is not found
        }
    }

    @Transactional
    public String register(String firstName, String lastName, String username, String emailAddress, String userType, String password, String bio, byte[] PFP){
        Optional<User> usernameCheck = userRepository.findByUsername(username);
        Optional<User> emailCheck = userRepository.findByEmailAddress(emailAddress);

        if(usernameCheck.isPresent()){
            return "username exist already";
        }

        if(emailCheck.isPresent()){
            return "email address is already used";
        }

        User newUser = new User();
        newUser.setFirst_name(firstName);
        newUser.setLast_name(lastName);
        newUser.setUsername(username);
        newUser.setEmailAddress(emailAddress);
        newUser.setUser_type(userType);
        newUser.setPassword(password);
        newUser.setBio(bio);
        newUser.setPFP(PFP);

        userRepository.save(newUser);
        return "User added";

           
    }

    public User findByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password)
                             .orElse(null);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                             .orElse(null);
    }

    @Transactional
    public String changeUsername(String oldUsername, String newUsername) {
        Optional<User> existingUser = userRepository.findByUsername(oldUsername);

        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setUsername(newUsername);
            userRepository.save(user);
            return "Username changed successfully";
        } else {
            return "User not found with the old username";
        }
    }

    @Transactional
    public String changePassword(String oldPassword, String newPassword) {
        Optional<User> existingUser = userRepository.findByPassword(oldPassword);

        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setPassword(newPassword);
            userRepository.save(user);
            return "Password changed successfully";
        } else {
            return "User not found with the old username";
        }
    }

    @Transactional
    public String changeBio(String newBio, String oldUsername) {
        Optional<User> existingUser = userRepository.findByUsername(oldUsername);

        if (existingUser.isPresent()) {
                User user = existingUser.get();
                user.setBio(newBio);
                userRepository.save(user);
                return "Bio changed successfully";
        } else {
            return "User not found with the old username";
        }
    }

    @Transactional
    public String changePFP(byte[] newPFP, String oldUsername) {
        Optional<User> existingUser = userRepository.findByUsername(oldUsername);

        if (existingUser.isPresent()) {
                User user = existingUser.get();
                user.setPFP(newPFP);
                userRepository.save(user);
                return "profile picture changed successfully";
        } else {
            return "User not found with the old username";
        }
    }

    @Transactional
    public String updatePoints(String username, int points) {
        User user = userRepository.findByUsername(username).orElse(null);
        if (user != null) {
            user.setPointsEarned(user.getPointsEarned() + points);
            userRepository.save(user);
            return "Points updated successfully";
        } else {
            return "User not found";
        }
    }
}


