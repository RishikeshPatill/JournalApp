package com.org.journalApp.controller;

import com.org.journalApp.entity.User;
import com.org.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;

    @GetMapping("/health-check")
    public String healthCheck(){
        return "App Health Okay Okay! Rishikesh u have made it, Go Beast.";
    }

    @PostMapping("/create-user")
    public ResponseEntity<User> createUser(@RequestBody User myUser) {
        try {
            userService.saveNewUser(myUser);
            return new ResponseEntity<>(myUser, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
