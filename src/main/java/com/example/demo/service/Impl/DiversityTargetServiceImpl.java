package com.example.demo.service.impl;

import com.example.demo.entity.DiversityTarget;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.DiversityTargetRepository;
import com.example.demo.service.DiversityTargetService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DiversityTargetServiceImpl implements DiversityTargetService {

    private final DiversityTargetRepository targetRepository;

    public DiversityTargetServiceImpl(DiversityTargetRepository targetRepository) {
        this.targetRepository = targetRepository;
    }

    @Override
    public DiversityTarget createTarget(DiversityTarget target) {
        // Validate percentage
        if (target.getTargetPercentage() < 0 || target.getTargetPercentage() > 100) {
            throw new BadRequestException("Target percentage must be between 0 and 100");
        }
        
        // Check for existing active target for same classification and year
        targetRepository.findByTargetYearAndClassificationId(
                target.getTargetYear(), 
                target.getClassification().getId()
        ).ifPresent(existingTarget -> {
            if (Boolean.TRUE.equals(existingTarget.getActive())) {
                throw new BadRequestException("Active target already exists for classification " + 
                        target.getClassification().getCode() + " in year " + target.getTargetYear());
            }
        });
        
        return targetRepository.save(target);
    }

    @Override
    public List<DiversityTarget> getTargetsByYear(Integer year) {
        return targetRepository.findByTargetYear(year);
    }

    @Override
    public void deactivateTarget(Long id) {
        DiversityTarget target = targetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Target not found with id: " + id));
        target.setActive(false);
        targetRepository.save(target);
    }

    @Override
    public List<DiversityTarget> getActiveTargets() {
        return targetRepository.findByActiveTrue();
    }
}