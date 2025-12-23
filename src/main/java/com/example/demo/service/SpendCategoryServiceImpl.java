package com.example.demo.service.impl;

import com.example.demo.entity.SpendCategory;
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
    public SpendCategory createCategory(SpendCategory category) {
        if (categoryRepository.findByName(category.getName()).isPresent()) {
            throw new RuntimeException("Category with this name already exists");
        }
        return categoryRepository.save(category);
    }
    
    @Override
    @Transactional(readOnly = true)
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
}