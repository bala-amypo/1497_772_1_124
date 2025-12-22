package com.example.demo.controller;

import com.example.demo.entity.SpendCategory;
import com.example.demo.service.SpendCategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class SpendCategoryController {

    private final SpendCategoryService service;

    public SpendCategoryController(SpendCategoryService service) {
        this.service = service;
    }

    @PostMapping
    public SpendCategory save(@RequestBody SpendCategory category) {
        return service.save(category);
    }

    @GetMapping("/{id}")
    public SpendCategory getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping
    public List<SpendCategory> getAll() {
        return service.getAll();
    }
}
