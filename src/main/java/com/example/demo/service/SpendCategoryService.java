package com.example.demo.service;

import java.util.List;

import com.example.demo.Entity.SpendCategory;

public interface SpendCategoryService {

    SpendCategory createCategory(SpendCategory category);

    SpendCategory updateCategory(Long id, SpendCategory category);

    SpendCategory getCategoryById(Long id);

    List<SpendCategory> getAllCategories();

    SpendCategory deactivateCategory(Long id);
}
