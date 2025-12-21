package com.example.demo.controller;

import com.example.demo.entity.SpendCategory;
import com.example.demo.service.SpendCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class SpendCategoryController {
    
    private final SpendCategoryService spendCategoryService;
    
    @Autowired
    public SpendCategoryController(SpendCategoryService spendCategoryService) {
        this.spendCategoryService = spendCategoryService;
    }
    
    @GetMapping
    public ResponseEntity<List<SpendCategory>> getAllCategories() {
        List<SpendCategory> categories = spendCategoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }
    
    @PutMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivateCategory(@PathVariable Long id) {
        spendCategoryService.deactivateCategory(id);
        return ResponseEntity.ok().build();
    }
}