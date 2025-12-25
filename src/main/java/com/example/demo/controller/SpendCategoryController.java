package com.example.demo.controller;

import com.example.demo.entity.SpendCategory;
import com.example.demo.service.SpendCategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class SpendCategoryController {
    private final SpendCategoryService service;
    
    public SpendCategoryController(SpendCategoryService service) {
        this.service = service;
    }
    
    @PostMapping
    public ResponseEntity<SpendCategory> createCategory(@RequestBody SpendCategory category) {
        return ResponseEntity.ok(service.createCategory(category));
    }
    
    @GetMapping("/active")
    public ResponseEntity<List<SpendCategory>> getActiveCategories() {
        return ResponseEntity.ok(service.getActiveCategories());
    }
}