 package com.example.demo.controller;

 import org.springframework.http.HttpStatus;
 import org.springframework.http.ResponseEntity;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.web.bind.annotation.*;
 import com.example.demo.model.User;
 import com.example.demo.service.UserService;
 import com.example.demo.model.User.ChangeUsernameRequest;

 @RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/authentication")
public class EditProfileController {

    private final UserService userService;

    @Autowired
    public EditProfileController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping(value = "/changeusername", consumes = "application/json")
    public ResponseEntity<String> changeUsername(@RequestBody User.ChangeUsernameRequest changeUsernameRequest) {
        try {
            String oldUsername = changeUsernameRequest.getOldUsername();
            String newUsername = changeUsernameRequest.getNewUsername();

            userService.changeUsername(oldUsername, newUsername);

            return ResponseEntity.ok("Username has been changed successfully");
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging purposes
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }

    @PutMapping(value = "/changepassword", consumes = "application/json")
    public ResponseEntity<String> changePassword(@RequestBody User.ChangePasswordRequest changePasswordRequest) {
        try {
            String oldPassword = changePasswordRequest.getOldPassword();
            String newPassword = changePasswordRequest.getNewPassword();

            userService.changePassword(oldPassword, newPassword);

            return ResponseEntity.ok("Password has been changed successfully");
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging purposes
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }

    @PutMapping(value = "/changebio", consumes = "application/json")
    public ResponseEntity<String> changeBio(@RequestBody User.ChangeBioRequest changeBioRequest) {
        try {
            String oldUsername = changeBioRequest.getOldUsername();
            String newBio = changeBioRequest.getNewBio();

            userService.changeBio(newBio, oldUsername);

            return ResponseEntity.ok("Bio has been changed successfully");
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging purposes
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }

    @PutMapping(value = "/changePFP", consumes = "application/json")
    public ResponseEntity<String> changePFP(@RequestBody User.ChangePFPRequest changePFPRequest) {
        try {
            String oldUsername = changePFPRequest.getOldUsername();
            byte[] newPFP = changePFPRequest.getNewPFP();

            userService.changePFP(newPFP, oldUsername);

            return ResponseEntity.ok("Profile picture has been changed successfully");
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging purposes
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }
}
