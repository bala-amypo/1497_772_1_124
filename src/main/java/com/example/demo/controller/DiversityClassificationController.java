package com.example.demo.controller;

import com.example.demo.entity.DiversityClassification;
import com.example.demo.service.DiversityClassificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/classifications")
public class DiversityClassificationController {
    
    private final DiversityClassificationService classificationService;
    
    @Autowired
    public DiversityClassificationController(DiversityClassificationService classificationService) {
        this.classificationService = classificationService;
    }
    
    @PostMapping
    public ResponseEntity<DiversityClassification> createClassification(@RequestBody DiversityClassification classification) {
        DiversityClassification createdClassification = classificationService.createClassification(classification);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdClassification);
    }
    
    @GetMapping
    public ResponseEntity<List<DiversityClassification>> getAllClassifications() {
        List<DiversityClassification> classifications = classificationService.getAllClassifications();
        return ResponseEntity.ok(classifications);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<DiversityClassification> getClassificationById(@PathVariable Long id) {
        DiversityClassification classification = classificationService.getClassificationById(id);
        return ResponseEntity.ok(classification);
    }
    
    @PutMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivateClassification(@PathVariable Long id) {
        classificationService.deactivateClassification(id);
        return ResponseEntity.ok().build();
    }
}