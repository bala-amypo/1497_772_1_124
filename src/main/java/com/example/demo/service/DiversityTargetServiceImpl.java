package com.example.demo.service.impl;

import com.example.demo.entity.DiversityTarget;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.DiversityTargetRepository;
import com.example.demo.service.DiversityTargetService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiversityTargetServiceImpl implements DiversityTargetService {

    private final DiversityTargetRepository repo;

    public DiversityTargetServiceImpl(DiversityTargetRepository repo) {
        this.repo = repo;
    }

    public DiversityTarget save(DiversityTarget target) {
        return repo.save(target);
    }

    public DiversityTarget getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Target not found"));
    }

    public List<DiversityTarget> getAll() {
        return repo.findAll();
    }
}
