package com.example.demo.service;

import com.example.demo.entity.DiversityTarget;
import java.util.List;

public interface DiversityTargetService {
    DiversityTarget save(DiversityTarget target);
    DiversityTarget getById(Long id);
    List<DiversityTarget> getAll();
}
