package com.example.demo.controller;

import com.example.demo.entity.UserAccount;
import com.example.demo.service.UserAccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserAccountController {

    private final UserAccountService service;

    public UserAccountController(UserAccountService service) {
        this.service = service;
    }

    @PostMapping
    public UserAccount save(@RequestBody UserAccount user) {
        return service.save(user);
    }

    @GetMapping("/{id}")
    public UserAccount getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping
    public List<UserAccount> getAll() {
        return service.getAll();
    }
}
