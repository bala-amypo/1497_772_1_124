package com.example.demo.controller;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.dto.JwtResponse;
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
    public ResponseEntity<JwtResponse> register(@RequestBody RegisterRequest registerRequest) {
        UserAccount userAccount = new UserAccount();
        userAccount.setFullName(registerRequest.getFullName());
        userAccount.setEmail(registerRequest.getEmail());
        userAccount.setPassword(registerRequest.getPassword());
        userAccount.setRole(registerRequest.getRole());
        
        UserAccount registeredUser = userAccountService.register(userAccount);
        
        String token = jwtUtil.generateToken(
            registeredUser.getId(),
            registeredUser.getEmail(),
            registeredUser.getRole()
        );
        
        JwtResponse response = new JwtResponse(
            token,
            registeredUser.getId(),
            registeredUser.getEmail(),
            registeredUser.getRole()
        );
        
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    loginRequest.getEmail(),
                    loginRequest.getPassword()
                )
            );
            
            SecurityContextHolder.getContext().setAuthentication(authentication);
            
            UserAccount userAccount = userAccountService.findByEmailOrThrow(loginRequest.getEmail());
            
            String token = jwtUtil.generateToken(
                userAccount.getId(),
                userAccount.getEmail(),
                userAccount.getRole()
            );
            
            JwtResponse response = new JwtResponse(
                token,
                userAccount.getId(),
                userAccount.getEmail(),
                userAccount.getRole()
            );
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new UnauthorizedException("Invalid email or password");
        }
    }
}