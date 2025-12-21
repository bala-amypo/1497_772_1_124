package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.UserAccount;
import com.example.demo.exception.BadRequestException;
import com.example.demo.service.UserAccountService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserAccountService userService;

    public AuthController(UserAccountService userService) {
        this.userService = userService;
    }

    // REGISTER
    @PostMapping("/register")
    public UserAccount register(@RequestBody UserAccount user) {
        return userService.register(user);
    }

    // LOGIN (plain email + password check)
    @PostMapping("/login")
    public Map<String, String> login(@RequestBody UserAccount user) {

        UserAccount dbUser =
                userService.findByEmailOrThrow(user.getEmail());

        if (!dbUser.getPassword().equals(user.getPassword())) {
            throw new BadRequestException("Invalid email or password");
        }

        Map<String, String> response = new HashMap<>();
        response.put("message", "Login successful");
        response.put("email", dbUser.getEmail());
        response.put("role", dbUser.getRole());

        return response;
    }
}
