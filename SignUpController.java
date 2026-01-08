package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.example.demo.dto.ResponseDTO;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/authentication")
public class SignUpController {

    private final UserService userService;

    // Constructor injection for UserService
    public SignUpController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/signup", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ResponseDTO> signUp(@RequestBody User request) {
    
        String statusMessage = userService.register(
                request.getFirst_name(),
                request.getLast_name(),
                request.getUsername(),
                request.getEmailAddress(),
                request.getUser_type(),
                request.getPassword(),
                request.getBio(),
                request.getPFP()
        );

        ResponseDTO response;

        switch (statusMessage) {
            case "User added":
                response = new ResponseDTO("Success", statusMessage);
                return new ResponseEntity<>(response, HttpStatus.CREATED);
            case "username exist already":
                response = new ResponseDTO("Username", statusMessage);
                return new ResponseEntity<>(response, HttpStatus.CONFLICT);
            case "email address is already used":
                response = new ResponseDTO("Email", statusMessage);
                return new ResponseEntity<>(response, HttpStatus.CONFLICT);
            default:
                response = new ResponseDTO("Error", "An unknown error occurred");
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
