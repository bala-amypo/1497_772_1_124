package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.DiversityClassification;
import com.example.demo.entity.DiversityTarget;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.DiversityClassificationRepository;
import com.example.demo.repository.DiversityTargetRepository;
import com.example.demo.service.DiversityTargetService;

@Service
public class DiversityTargetServiceImpl
        implements DiversityTargetService {

    private final DiversityTargetRepository targetRepository;
    private final DiversityClassificationRepository classificationRepository;

    public DiversityTargetServiceImpl(
            DiversityTargetRepository targetRepository,
            DiversityClassificationRepository classificationRepository) {

        this.targetRepository = targetRepository;
        this.classificationRepository = classificationRepository;
    }

    @Override
    public DiversityTarget createTarget(DiversityTarget target) {

        if (target.getTargetPercentage() == null ||
                target.getTargetPercentage() < 0 ||
                target.getTargetPercentage() > 100) {
            throw new BadRequestException(
                    "Target percentage must be between 0 and 100");
        }

        DiversityClassification classification =
                classificationRepository.findById(
                        target.getClassification().getId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Classification not found"));

        target.setClassification(classification);

        return targetRepository.save(target);
    }

    @Override
    public List<DiversityTarget> getTargetsByYear(int year) {
        return targetRepository.findByTargetYear(year);
    }

    @Override
    public void deactivateTarget(Long id) {
        DiversityTarget target = targetRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Target not found with id " + id));

        target.setActive(false);
        targetRepository.save(target);
    }
}
