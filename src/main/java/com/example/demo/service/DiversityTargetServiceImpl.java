package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.DiversityTarget;
import com.example.demo.repository.DiversityTargetRepository;

@Service
public class DiversityTargetServiceImpl implements DiversityTargetService {

    @Autowired
    DiversityTargetRepository repo;

    @Override
    public DiversityTarget createTarget(DiversityTarget target) {
        return repo.save(target);
    }

    @Override
    public DiversityTarget updateTarget(Long id, DiversityTarget target) {
        DiversityTarget existing = repo.findById(id).orElse(null);

        if (existing != null) {
            existing.setTargetYear(target.getTargetYear());
            existing.setClassification(target.getClassification());
            existing.setTargetPercentage(target.getTargetPercentage());
            return repo.save(existing);
        }
        return null;
    }

    @Override
    public List<DiversityTarget> getTargetsByYear(Integer year) {
        return repo.findByTargetYear(year);
    }

    @Override
    public List<DiversityTarget> getAllTargets() {
        return repo.findAll();
    }

    @Override
    public DiversityTarget deactivateTarget(Long id) {
        DiversityTarget target = repo.findById(id).orElse(null);

        if (target != null) {
            target.setActive(false);
            return repo.save(target);
        }
        return null;
    }
}
