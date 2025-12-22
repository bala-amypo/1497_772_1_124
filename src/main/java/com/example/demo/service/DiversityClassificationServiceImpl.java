package com.example.demo.service.impl;

import com.example.demo.entity.DiversityClassification;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.DiversityClassificationRepository;
import com.example.demo.service.DiversityClassificationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiversityClassificationServiceImpl
        implements DiversityClassificationService {

    private final DiversityClassificationRepository repo;

    public DiversityClassificationServiceImpl(
            DiversityClassificationRepository repo) {
        this.repo = repo;
    }

    public DiversityClassification save(DiversityClassification classification) {
        return repo.save(classification);
    }

    public DiversityClassification getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Classification not found"));
    }

    public List<DiversityClassification> getAll() {
        return repo.findAll();
    }
}
