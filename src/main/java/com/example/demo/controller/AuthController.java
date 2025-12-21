package com.example.demo.controller;

import com.example.demo.entity.UserAccount;
import com.example.demo.exception.UnauthorizedException;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    
    private final UserAccountService userAccountService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    
    @Autowired
    public AuthController(UserAccountService userAccountService,
                         AuthenticationManager authenticationManager,
                         JwtUtil jwtUtil) {
        this.userAccountService = userAccountService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }
    
    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody UserAccount userAccount) {
        UserAccount registeredUser = userAccountService.register(userAccount);
        
        String token = jwtUtil.generateToken(
            registeredUser.getId(),
            registeredUser.getEmail(),
            registeredUser.getRole()
        );
        
        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("userId", registeredUser.getId());
        response.put("email", registeredUser.getEmail());
        response.put("role", registeredUser.getRole());
        
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> loginRequest) {
        try {
            String email = loginRequest.get("email");
            String password = loginRequest.get("password");
            
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
            );
            
            SecurityContextHolder.getContext().setAuthentication(authentication);
            
            UserAccount userAccount = userAccountService.findByEmailOrThrow(email);
            
            String token = jwtUtil.generateToken(
                userAccount.getId(),
                userAccount.getEmail(),
                userAccount.getRole()
            );
            
            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("userId", userAccount.getId());
            response.put("email", userAccount.getEmail());
            response.put("role", userAccount.getRole());
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new UnauthorizedException("Invalid email or password");
        }
    }
}