package com.example.demo.controller;

import com.example.demo.entity.DiversityClassification;
import com.example.demo.service.DiversityClassificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/classifications")
public class DiversityClassificationController {
    
    private final DiversityClassificationService classificationService;
    
    public DiversityClassificationController(DiversityClassificationService classificationService) {
        this.classificationService = classificationService;
    }
    
    @PostMapping
    public ResponseEntity<DiversityClassification> createClassification(@RequestBody DiversityClassification classification) {
        DiversityClassification created = classificationService.createClassification(classification);
        return ResponseEntity.ok(created);
    }
    
    @GetMapping
    public ResponseEntity<List<DiversityClassification>> getAllClassifications() {
        List<DiversityClassification> classifications = classificationService.getAllClassifications();
        return ResponseEntity.ok(classifications);
    }
    
    @GetMapping("/active")
    public ResponseEntity<List<DiversityClassification>> getActiveClassifications() {
        List<DiversityClassification> classifications = classificationService.getActiveClassifications();
        return ResponseEntity.ok(classifications);
    }
    
    @PutMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivateClassification(@PathVariable Long id) {
        classificationService.deactivateClassification(id);
        return ResponseEntity.ok().build();
    }
}