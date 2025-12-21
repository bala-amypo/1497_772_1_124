package com.example.demo.controller;

import com.example.demo.entity.DiversityTarget;
import com.example.demo.service.DiversityTargetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/targets")
public class DiversityTargetController {
    
    private final DiversityTargetService diversityTargetService;
    
    @Autowired
    public DiversityTargetController(DiversityTargetService diversityTargetService) {
        this.diversityTargetService = diversityTargetService;
    }
    
    @PostMapping
    public ResponseEntity<DiversityTarget> createTarget(@RequestBody DiversityTarget target) {
        // Simple validation
        if (target.getTargetPercentage() > 100) {
            return ResponseEntity.badRequest().build();
        }
        
        DiversityTarget createdTarget = diversityTargetService.createTarget(target);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTarget);
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