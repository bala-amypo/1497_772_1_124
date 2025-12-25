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
    
    @GetMapping("/active")
    public ResponseEntity<List<DiversityClassification>> getActiveClassifications() {
        return ResponseEntity.ok(service.getActiveClassifications());
    }
}