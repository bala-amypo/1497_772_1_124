package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.UserAccount;
import com.example.demo.repository.UserAccountRepository;

@Service
public class UserAccountServiceImpl implements UserAccountService {

    @Autowired
    UserAccountRepository repo;

    @Override
    public UserAccount register(UserAccount user) {
        // Encode password (mock)
        user.setPassword(user.getPassword() + "_ENC");
        return repo.save(user);
    }

    @Override
    public UserAccount findByEmail(String email) {
        return repo.findByEmail(email).orElse(null);
    }
}
