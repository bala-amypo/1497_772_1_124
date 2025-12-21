package com.example.demo.service.impl;

import com.example.demo.entity.DiversityTarget;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.DiversityTargetRepository;
import com.example.demo.service.DiversityTargetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class DiversityTargetServiceImpl implements DiversityTargetService {
    
    private final DiversityTargetRepository diversityTargetRepository;
    
    @Autowired
    public DiversityTargetServiceImpl(DiversityTargetRepository diversityTargetRepository) {
        this.diversityTargetRepository = diversityTargetRepository;
    }
    
    @Override
    @Transactional
    public DiversityTarget createTarget(DiversityTarget target) {
        // Validate percentage range
        if (target.getTargetPercentage() == null || 
            target.getTargetPercentage() < 0 || 
            target.getTargetPercentage() > 100) {
            throw new BadRequestException("Target percentage must be between 0 and 100");
        }
        
        return diversityTargetRepository.save(target);
    }
    
    @Override
    public List<DiversityTarget> getTargetsByYear(int year) {
        return diversityTargetRepository.findByTargetYear(year);
    }
    
    @Override
    @Transactional
    public void deactivateTarget(Long id) {
        DiversityTarget target = diversityTargetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Diversity target not found with id: " + id));
        target.setActive(false);
        diversityTargetRepository.save(target);
    }
}