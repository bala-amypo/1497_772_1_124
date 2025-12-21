package com.example.demo.service.impl;

import com.example.demo.entity.SpendCategory;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.SpendCategoryRepository;
import com.example.demo.service.SpendCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class SpendCategoryServiceImpl implements SpendCategoryService {
    
    private final SpendCategoryRepository spendCategoryRepository;
    
    @Autowired
    public SpendCategoryServiceImpl(SpendCategoryRepository spendCategoryRepository) {
        this.spendCategoryRepository = spendCategoryRepository;
    }
    
    @Override
    public List<SpendCategory> getAllCategories() {
        return spendCategoryRepository.findAll();
    }
    
    @Override
    @Transactional
    public void deactivateCategory(Long id) {
        SpendCategory category = spendCategoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Spend category not found with id: " + id));
        category.setActive(false);
        spendCategoryRepository.save(category);
    }
}