package com.example.demo.service.impl;

import com.example.demo.entity.DiversityClassification;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.DiversityClassificationRepository;
import com.example.demo.service.DiversityClassificationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class DiversityClassificationServiceImpl implements DiversityClassificationService {
    
    private final DiversityClassificationRepository classificationRepository;
    
    public DiversityClassificationServiceImpl(DiversityClassificationRepository classificationRepository) {
        this.classificationRepository = classificationRepository;
    }
    
    @Override
    public DiversityClassification createClassification(DiversityClassification classification) {
        if (classificationRepository.findByCode(classification.getCode()).isPresent()) {
            throw new BadRequestException("Classification with this code already exists");
        }
        return classificationRepository.save(classification);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<DiversityClassification> getAllClassifications() {
        return classificationRepository.findAll();
    }
    
    @Override
    public void deactivateClassification(Long id) {
        DiversityClassification classification = classificationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Classification not found with id: " + id));
        classification.setActive(false);
        classificationRepository.save(classification);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<DiversityClassification> getActiveClassifications() {
        return classificationRepository.findByActiveTrue();
    }
}