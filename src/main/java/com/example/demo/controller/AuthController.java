package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.UserAccount;
import com.example.demo.service.UserAccountService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    UserAccountService service;

    @PostMapping("/register")
    public UserAccount register(@RequestBody UserAccount user) {
        return service.register(user);
    }

   
    @PostMapping("/login")
    public String login(@RequestBody UserAccount request) {

        UserAccount user = service.findByEmail(request.getEmail());

        if (user == null) {
            return "User not found";
        }

        if (user.getPassword().equals(request.getPassword() + "_ENC")) {
            return "Login Successful";
        } else {
            return "Invalid Credentials";
        }
    }
}
