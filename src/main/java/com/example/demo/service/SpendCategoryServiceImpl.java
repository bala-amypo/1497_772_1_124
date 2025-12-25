package com.example.demo.service.impl;

import com.example.demo.entity.SpendCategory;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.SpendCategoryRepository;
import com.example.demo.service.SpendCategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SpendCategoryServiceImpl implements SpendCategoryService {

    private final SpendCategoryRepository categoryRepository;

    public SpendCategoryServiceImpl(SpendCategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<SpendCategory> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public void deactivateCategory(Long id) {
        SpendCategory category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
        category.setActive(false);
        categoryRepository.save(category);
    }

    @Override
    public SpendCategory createCategory(SpendCategory category) {
        if (categoryRepository.findByName(category.getName()).isPresent()) {
            throw new BadRequestException("Category name already exists: " + category.getName());
        }
        return categoryRepository.save(category);
    }

    @Override
    public List<SpendCategory> getActiveCategories() {
        return categoryRepository.findByActiveTrue();
    }
}