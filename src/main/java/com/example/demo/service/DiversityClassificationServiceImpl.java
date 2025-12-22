package com.example.demo.service.impl;
import com.example.demo.entity.DiversityClassification;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.DiversityClassificationRepository;
import com.example.demo.service.DiversityClassificationService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DiversityClassificationServiceImpl implements DiversityClassificationService {
    private final DiversityClassificationRepository diversityClassificationRepository;

    public DiversityClassificationServiceImpl(DiversityClassificationRepository diversityClassificationRepository) {
        this.diversityClassificationRepository = diversityClassificationRepository;
    }

    @Override
    public List<DiversityClassification> getAllClassifications() {
        return diversityClassificationRepository.findAll();
    }

    @Override
    public void deactivateClassification(Long id) {
        DiversityClassification classification = diversityClassificationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Classification not found with id: " + id));
        classification.setActive(false);
        diversityClassificationRepository.save(classification);
    }
}

// Service/impl/SpendCategoryServiceImpl.java
package com.example.demo.service.impl;
import com.example.demo.entity.SpendCategory;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.SpendCategoryRepository;
import com.example.demo.service.SpendCategoryService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SpendCategoryServiceImpl implements SpendCategoryService {
    private final SpendCategoryRepository spendCategoryRepository;

    public SpendCategoryServiceImpl(SpendCategoryRepository spendCategoryRepository) {
        this.spendCategoryRepository = spendCategoryRepository;
    }

    @Override
    public List<SpendCategory> getAllCategories() {
        return spendCategoryRepository.findAll();
    }

    @Override
    public void deactivateCategory(Long id) {
        SpendCategory category = spendCategoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
        category.setActive(false);
        spendCategoryRepository.save(category);
    }
}
