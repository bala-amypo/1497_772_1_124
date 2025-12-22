package com.example.demo.controller;
import com.example.demo.entity.SpendCategory;
import com.example.demo.service.SpendCategoryService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class SpendCategoryController {
    private final SpendCategoryService spendCategoryService;

    public SpendCategoryController(SpendCategoryService spendCategoryService) {
        this.spendCategoryService = spendCategoryService;
    }

    @GetMapping
    public List<SpendCategory> getAllCategories() {
        return spendCategoryService.getAllCategories();
    }

    @PutMapping("/{id}/deactivate")
    public void deactivateCategory(@PathVariable Long id) {
        spendCategoryService.deactivateCategory(id);
    }
}