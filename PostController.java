package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ResponseDTO;
import com.example.demo.model.Post;
import com.example.demo.model.User;


import com.example.demo.service.PostService;
import com.example.demo.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RequestMapping("/posts")
public class PostController {

     private final PostService postService;

    // Constructor injection for PostService
    public PostController(PostService postService) {
        this.postService = postService;
    }

    // @DeleteMapping(value = "/{postId}", produces = "application/json")
    // public ResponseEntity<ResponseDTO> deletePost(@PathVariable Long postId) {
    
    //     boolean deleted = postService.deletePost(postId);

    //     if (deleted) {
    //         ResponseDTO response = new ResponseDTO("Success", "Post deleted successfully");
    //         return new ResponseEntity<>(response, HttpStatus.OK);
    //     } else {
    //         ResponseDTO response = new ResponseDTO("Error", "Failed to delete post");
    //         return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    //     }
    // }


    @GetMapping("/viewposts")
    public ResponseEntity<List<Post>> viewPosts() {
        List<Post> posts = postService.getAllPosts();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }
    
}
