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
