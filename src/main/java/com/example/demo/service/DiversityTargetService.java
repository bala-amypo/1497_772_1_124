package com.example.demo.service;

import java.util.List;

import com.example.demo.Entity.DiversityTarget;

public interface DiversityTargetService {

    DiversityTarget createTarget(DiversityTarget target);

    DiversityTarget updateTarget(Long id, DiversityTarget target);

    List<DiversityTarget> getTargetsByYear(Integer year);

    List<DiversityTarget> getAllTargets();

    DiversityTarget deactivateTarget(Long id);
}
