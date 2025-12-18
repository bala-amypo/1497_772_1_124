package com.example.demo.service;

import java.util.List;

import com.example.demo.Entity.DiversityClassification;

public interface DiversityClassificationService {

    DiversityClassification createClassification(DiversityClassification c);

    DiversityClassification updateClassification(Long id, DiversityClassification c);

    List<DiversityClassification> getAllClassifications();

    DiversityClassification getById(Long id);

    DiversityClassification deactivateClassification(Long id);
}
