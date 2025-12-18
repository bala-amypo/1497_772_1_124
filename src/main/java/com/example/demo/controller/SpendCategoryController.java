package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Entity.SpendCategory;
import com.example.demo.service.SpendCategoryService;

@RestController
@RequestMapping("/api/categories")
public class SpendCategoryController {

    @Autowired
    SpendCategoryService categoryService;

   
    @PostMapping
    public SpendCategory create(@RequestBody SpendCategory category) {
        return categoryService.createCategory(category);
    }

   
    @PutMapping("/{id}")
    public SpendCategory update(@PathVariable Long id, @RequestBody SpendCategory category) {
        return categoryService.updateCategory(id, category);
    }

   
    @GetMapping("/{id}")
    public SpendCategory getById(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }

    
    @GetMapping
    public List<SpendCategory> getAll() {
        return categoryService.getAllCategories();
    }

  
    @PutMapping("/{id}/deactivate")
    public SpendCategory deactivate(@PathVariable Long id) {
        return categoryService.deactivateCategory(id);
    }
}
