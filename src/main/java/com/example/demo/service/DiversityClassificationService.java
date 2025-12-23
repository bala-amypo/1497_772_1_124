package com.example.demo.service;

import com.example.demo.entity.DiversityClassification;
import java.util.List;

public interface DiversityClassificationService {
    DiversityClassification createClassification(DiversityClassification classification);
    List<DiversityClassification> getAllClassifications();
    void deactivateClassification(Long id);
    List<DiversityClassification> getActiveClassifications();
}