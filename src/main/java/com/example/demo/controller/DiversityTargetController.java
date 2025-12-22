package com.example.demo.controller;

import com.example.demo.entity.DiversityTarget;
import com.example.demo.entity.DiversityClassification;
import com.example.demo.service.DiversityTargetService;
import com.example.demo.service.DiversityClassificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/targets")
public class DiversityTargetController {
    
    private final DiversityTargetService diversityTargetService;
    private final DiversityClassificationService classificationService;
    
    @Autowired
    public DiversityTargetController(
            DiversityTargetService diversityTargetService,
            DiversityClassificationService classificationService) {
        this.diversityTargetService = diversityTargetService;
        this.classificationService = classificationService;
    }
    
    @PostMapping
    public ResponseEntity<DiversityTarget> createTarget(@RequestBody DiversityTarget targetRequest) {
        try {
            // Check if classification ID is provided
            if (targetRequest.getClassification() == null || targetRequest.getClassification().getId() == null) {
                throw new IllegalArgumentException("Classification ID is required");
            }
            
            // Get the classification ID from the request
            Long classificationId = targetRequest.getClassification().getId();
            
            // Fetch the full classification from database
            DiversityClassification classification = classificationService.getClassificationById(classificationId);
            
            // Create a new target with only necessary fields
            DiversityTarget target = new DiversityTarget();
            target.setTargetYear(targetRequest.getTargetYear());
            target.setTargetPercentage(targetRequest.getTargetPercentage());
            target.setClassification(classification);
            
            // Set active if provided, otherwise default to true
            if (targetRequest.getActive() != null) {
                target.setActive(targetRequest.getActive());
            } else {
                target.setActive(true);
            }
            
            DiversityTarget createdTarget = diversityTargetService.createTarget(target);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdTarget);
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    
    @GetMapping("/year/{year}")
    public ResponseEntity<List<DiversityTarget>> getTargetsByYear(@PathVariable int year) {
        List<DiversityTarget> targets = diversityTargetService.getTargetsByYear(year);
        return ResponseEntity.ok(targets);
    }
    
    @PutMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivateTarget(@PathVariable Long id) {
        diversityTargetService.deactivateTarget(id);
        return ResponseEntity.ok().build();
    }
}