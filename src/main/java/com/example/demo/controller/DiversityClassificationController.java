package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Entity.DiversityClassificationEntity;
import com.example.demo.service.DiversityClassificationService;

@RestController
@RequestMapping("/api/diversity")
public class DiversityClassificationController {

    @Autowired
    private DiversityClassificationService diversityService;

   
    @PostMapping
    public ResponseEntity<DiversityClassificationEntity> createDiversity(
            @RequestBody DiversityClassificationEntity diversity) {
        DiversityClassificationEntity created = diversityService.createDiversity(diversity);
        return ResponseEntity.ok(created);
    }

    
    @GetMapping
    public ResponseEntity<List<DiversityClassificationEntity>> getAllDiversity() {
        List<DiversityClassificationEntity> list = diversityService.getAllDiversity();
        return ResponseEntity.ok(list);
    }

   
    @GetMapping("/{id}")
    public ResponseEntity<DiversityClassificationEntity> getDiversityById(@PathVariable Long id) {
        DiversityClassificationEntity diversity = diversityService.getDiversityById(id);
        if (diversity != null) {
            return ResponseEntity.ok(diversity);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<DiversityClassificationEntity> updateDiversity(
            @PathVariable Long id,
            @RequestBody DiversityClassificationEntity diversity) {
        DiversityClassificationEntity updated = diversityService.updateDiversity(id, diversity);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDiversity(@PathVariable Long id) {
        diversityService.deleteDiversity(id);
        return ResponseEntity.noContent().build();
    }
}
