package com.example.demo.service.impl;

import com.example.demo.entity.DiversityClassification;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.DiversityClassificationRepository;
import com.example.demo.service.DiversityClassificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class DiversityClassificationServiceImpl implements DiversityClassificationService {
    
    private final DiversityClassificationRepository classificationRepository;
    
    @Autowired
    public DiversityClassificationServiceImpl(DiversityClassificationRepository classificationRepository) {
        this.classificationRepository = classificationRepository;
    }
    
    @Override
    @Transactional
    public DiversityClassification createClassification(DiversityClassification classification) {
        // Validate code
        if (classification.getCode() == null || classification.getCode().trim().isEmpty()) {
            throw new IllegalArgumentException("Classification code is required");
        }
        
        // Convert code to uppercase
        classification.setCode(classification.getCode().toUpperCase());
        
        // Set active if null
        if (classification.getActive() == null) {
            classification.setActive(true);
        }
        
        return classificationRepository.save(classification);
    }
    
    @Override
    public List<DiversityClassification> getAllClassifications() {
        return classificationRepository.findAll();
    }
    
    @Override
    public DiversityClassification getClassificationById(Long id) {
        return classificationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Diversity classification not found with id: " + id));
    }
    
    @Override
    @Transactional
    public void deactivateClassification(Long id) {
        DiversityClassification classification = classificationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Diversity classification not found with id: " + id));
        classification.setActive(false);
        classificationRepository.save(classification);
    }
}