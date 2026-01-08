package com.example.demo.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.model.User;

import com.example.demo.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RequestMapping("/authentication")
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    // @PostMapping(value = "/login", consumes = "application/json")
    // public ResponseEntity<String> login1(@RequestBody User loginRequest) {
    //     String username = loginRequest.getUsername();
    //     String password = loginRequest.getPassword();

    //     User user = userRepository.findByUsernameAndPassword(username, password);

    //     if (user != null) {
    //         return ResponseEntity.ok("200 OK"); 
    //     } else {
    //         return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
    //     }
    // }


    @PostMapping(value = "/login", consumes = "application/json")
    public ResponseEntity<String> login(@RequestBody User user){

        User user1 = userService.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        if(user1==null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok("Success");


    }


}
