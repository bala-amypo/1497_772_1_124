package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.SpendCategory;

public interface SpendCategoryService {

    List<SpendCategory> getAllCategories();

    void deactivateCategory(Long id);
}
