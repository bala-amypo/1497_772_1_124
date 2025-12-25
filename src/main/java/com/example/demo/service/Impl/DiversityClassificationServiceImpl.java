package com.example.demo.service.impl;

import com.example.demo.entity.DiversityClassification;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.DiversityClassificationRepository;
import com.example.demo.service.DiversityClassificationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class DiversityClassificationServiceImpl implements DiversityClassificationService {
    private final DiversityClassificationRepository repository;
    
    public DiversityClassificationServiceImpl(DiversityClassificationRepository repository) {
        this.repository = repository;
    }
    
    @Override
    public DiversityClassification createClassification(DiversityClassification classification) {
        return repository.save(classification);
    }
    
    @Override
    public List<DiversityClassification> getActiveClassifications() {
        return repository.findByActiveTrue();
    }
    
    @Override
    public DiversityClassification deactivateClassification(Long id) {
        DiversityClassification classification = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Classification not found"));
        classification.setActive(false);
        return repository.save(classification);
    }
}