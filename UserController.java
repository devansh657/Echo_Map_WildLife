package com.example.demo.controller;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Rewards;
import com.example.demo.model.User;
import com.example.demo.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/user")
public class UserController {
    
    private UserService userService;

    // Constructor injection for UserService
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/find")
        public User getUserByUsername(@RequestParam String username) {
            User user1 = userService.findByUsername(username);
            if(user1==null){
                return null;
            }
            return user1; 
        }
        
    @GetMapping(value = "/rewards") 
    public List<Rewards> getRewardsByUsername(@RequestParam String username) {
        List<Rewards> rewards = userService.findRewardsByUsername(username);
        return rewards;
    } 
}
