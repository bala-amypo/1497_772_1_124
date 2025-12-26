package com.example.demo.controller;

import com.example.demo.entity.DiversityClassification;
import com.example.demo.service.DiversityClassificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/classifications")
public class DiversityClassificationController {
    private final DiversityClassificationService service;
    
    public DiversityClassificationController(DiversityClassificationService service) {
        this.service = service;
    }
    
    @PostMapping
    public ResponseEntity<DiversityClassification> createClassification(@RequestBody DiversityClassification classification) {
        return ResponseEntity.ok(service.createClassification(classification));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<DiversityClassification> updateClassification(@PathVariable Long id, @RequestBody DiversityClassification classification) {
        return ResponseEntity.ok(service.updateClassification(id, classification));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<DiversityClassification> getClassificationById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getClassificationById(id));
    }
    
    @GetMapping
    public ResponseEntity<List<DiversityClassification>> getAllClassifications() {
        return ResponseEntity.ok(service.getAllClassifications());
    }
    
    @PutMapping("/{id}/deactivate")
    public ResponseEntity<DiversityClassification> deactivateClassification(@PathVariable Long id) {
        return ResponseEntity.ok(service.deactivateClassification(id));
    }
}