package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.DiversityClassification;

public interface DiversityClassificationService {

    List<DiversityClassification> getAllClassifications();

    void deactivateClassification(Long id);
}
