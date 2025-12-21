package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.DiversityTarget;

public interface DiversityTargetService {

    DiversityTarget createTarget(DiversityTarget target);

    List<DiversityTarget> getTargetsByYear(int year);

    void deactivateTarget(Long id);
}
