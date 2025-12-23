package com.example.demo.controller;

import com.example.demo.entity.SpendCategory;
import com.example.demo.service.SpendCategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class SpendCategoryController {
    
    private final SpendCategoryService categoryService;
    
    public SpendCategoryController(SpendCategoryService categoryService) {
        this.categoryService = categoryService;
    }
    
    @PostMapping
    public ResponseEntity<SpendCategory> createCategory(@RequestBody SpendCategory category) {
        SpendCategory created = categoryService.createCategory(category);
        return ResponseEntity.ok(created);
    }
    
    @GetMapping
    public ResponseEntity<List<SpendCategory>> getAllCategories() {
        List<SpendCategory> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }
    
    @PutMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivateCategory(@PathVariable Long id) {
        categoryService.deactivateCategory(id);
        return ResponseEntity.ok().build();
    }
}