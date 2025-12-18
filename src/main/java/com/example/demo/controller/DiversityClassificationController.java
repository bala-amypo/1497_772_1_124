package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Entity.DiversityClassification;
import com.example.demo.service.DiversityClassificationService;

@RestController
@RequestMapping("/api/classifications")
public class DiversityClassificationController {

    @Autowired
    DiversityClassificationService service;

  
    @PostMapping
    public DiversityClassification create(@RequestBody DiversityClassification c) {
        return service.createClassification(c);
    }

   
    @PutMapping("/{id}")
    public DiversityClassification update(@PathVariable Long id,
                                          @RequestBody DiversityClassification c) {
        return service.updateClassification(id, c);
    }

   
    @GetMapping("/{id}")
    public DiversityClassification getById(@PathVariable Long id) {
        return service.getById(id);
    }

    
    @GetMapping
    public List<DiversityClassification> getAll() {
        return service.getAllClassifications();
    }

  
    @PutMapping("/{id}/deactivate")
    public DiversityClassification deactivate(@PathVariable Long id) {
        return service.deactivateClassification(id);
    }
}
