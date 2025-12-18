package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.DiversityClassification;
import com.example.demo.repository.DiversityClassificationRepository;

@Service
public class DiversityClassificationServiceImpl
        implements DiversityClassificationService {

    @Autowired
    DiversityClassificationRepository repo;

    @Override
    public DiversityClassification createClassification(DiversityClassification c) {
        return repo.save(c);
    }

    @Override
    public DiversityClassification updateClassification(Long id, DiversityClassification c) {
        DiversityClassification existing = repo.findById(id).orElse(null);

        if (existing != null) {
            existing.setCode(c.getCode());
            existing.setDescription(c.getDescription());
            return repo.save(existing);
        }
        return null;
    }

    @Override
    public List<DiversityClassification> getAllClassifications() {
        return repo.findAll();
    }

    @Override
    public DiversityClassification getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public DiversityClassification deactivateClassification(Long id) {
        DiversityClassification dc = repo.findById(id).orElse(null);

        if (dc != null) {
            dc.setActive(false);
            return repo.save(dc);
        }
        return null;
    }
}
