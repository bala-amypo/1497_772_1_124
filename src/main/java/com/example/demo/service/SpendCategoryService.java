package com.example.demo.service;

import com.example.demo.entity.SpendCategory;
import java.util.List;

public interface SpendCategoryService {
    SpendCategory save(SpendCategory category);
    SpendCategory getById(Long id);
    List<SpendCategory> getAll();
}
