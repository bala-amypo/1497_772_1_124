package com.example.demo.service.impl;

import com.example.demo.entity.SpendCategory;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.SpendCategoryRepository;
import com.example.demo.service.SpendCategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpendCategoryServiceImpl implements SpendCategoryService {

    private final SpendCategoryRepository repo;

    public SpendCategoryServiceImpl(SpendCategoryRepository repo) {
        this.repo = repo;
    }

    public SpendCategory save(SpendCategory category) {
        return repo.save(category);
    }

    public SpendCategory getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
    }

    public List<SpendCategory> getAll() {
        return repo.findAll();
    }
}
