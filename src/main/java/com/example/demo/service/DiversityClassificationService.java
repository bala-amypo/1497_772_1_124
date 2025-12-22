package com.example.demo.service;

import com.example.demo.entity.DiversityClassification;
import java.util.List;

public interface DiversityClassificationService {
    DiversityClassification save(DiversityClassification classification);
    DiversityClassification getById(Long id);
    List<DiversityClassification> getAll();
}
