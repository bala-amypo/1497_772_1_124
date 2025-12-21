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
    public List<DiversityClassification> getAllClassifications() {
        return classificationRepository.findAll();
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