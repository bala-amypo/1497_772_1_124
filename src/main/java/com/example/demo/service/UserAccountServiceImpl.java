package com.example.demo.service.impl;

import com.example.demo.entity.UserAccount;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.service.UserAccountService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserAccountServiceImpl implements UserAccountService {
    
    private final UserAccountRepository userAccountRepository;
    private final PasswordEncoder passwordEncoder;
    
    public UserAccountServiceImpl(UserAccountRepository userAccountRepository, 
                                 PasswordEncoder passwordEncoder) {
        this.userAccountRepository = userAccountRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    @Override
    public UserAccount register(UserAccount user) {
        // Check if email already exists
        if (userAccountRepository.existsByEmail(user.getEmail())) {
            throw new BadRequestException("Email already registered");
        }
        
        // Set default role if not provided
        if (user.getRole() == null || user.getRole().trim().isEmpty()) {
            user.setRole("USER");
        }
        
        // Encode password
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        
        return userAccountRepository.save(user