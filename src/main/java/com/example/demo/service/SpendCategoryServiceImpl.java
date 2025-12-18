package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.SpendCategory;
import com.example.demo.repository.SpendCategoryRepository;

@Service
public class SpendCategoryServiceImpl implements SpendCategoryService {

    @Autowired
    SpendCategoryRepository categoryRepo;

    @Override
    public SpendCategory createCategory(SpendCategory category) {
        return categoryRepo.save(category);
    }

    @Override
    public SpendCategory updateCategory(Long id, SpendCategory category) {
        SpendCategory existing = categoryRepo.findById(id).orElse(null);

        if (existing != null) {
            existing.setName(category.getName());
            existing.setDescription(category.getDescription());
            return categoryRepo.save(existing);
        }
        return null;
    }

    @Override
    public SpendCategory getCategoryById(Long id) {
        return categoryRepo.findById(id).orElse(null);
    }

    @Override
    public List<SpendCategory> getAllCategories() {
        return categoryRepo.findAll();
    }

    @Override
    public SpendCategory deactivateCategory(Long id) {
        SpendCategory category = categoryRepo.findById(id).orElse(null);

        if (category != null) {
            category.setActive(false);
            return categoryRepo.save(category);
        }
        return null;
    }
}
