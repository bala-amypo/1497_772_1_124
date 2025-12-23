package com.example.demo.controller;

import com.example.demo.entity.DiversityTarget;
import com.example.demo.service.DiversityTargetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/targets")
public class DiversityTargetController {
    
    private final DiversityTargetService targetService;
    
    public DiversityTargetController(DiversityTargetService targetService) {
        this.targetService = targetService;
    }
    
    @PostMapping
    public ResponseEntity<DiversityTarget> createTarget(@RequestBody DiversityTarget target) {
        DiversityTarget created = targetService.createTarget(target);
        return ResponseEntity.ok(created);
    }
    
    @GetMapping("/year/{year}")
    public ResponseEntity<List<DiversityTarget>> getTargetsByYear(@PathVariable Integer year) {
        List<DiversityTarget> targets = targetService.getTargetsByYear(year);
        return ResponseEntity.ok(targets);
    }
    
    @GetMapping("/active")
    public ResponseEntity<List<DiversityTarget>> getActiveTargets() {
        List<DiversityTarget> targets = targetService.getActiveTargets();
        return ResponseEntity.ok(targets);
    }
    
    @PutMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivateTarget(@PathVariable Long id) {
        targetService.deactivateTarget(id);
        return ResponseEntity.ok().build();
    }
}