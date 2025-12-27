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
    
    @PutMapping("/{id}")
    public ResponseEntity<SpendCategory> updateCategory(@PathVariable Long id, @RequestBody SpendCategory category) {
        return ResponseEntity.ok(service.updateCategory(id, category));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<SpendCategory> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getCategoryById(id));
    }
    
    @GetMapping
    public ResponseEntity<List<SpendCategory>> getAllCategories() {
        return ResponseEntity.ok(service.getAllCategories());
    }
    
    @PutMapping("/{id}/deactivate")
    public ResponseEntity<SpendCategory> deactivateCategory(@PathVariable Long id) {
        return ResponseEntity.ok(service.deactivateCategory(id));
    }
}