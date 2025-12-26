package com.example.demo.controller;

import com.example.demo.entity.DiversityTarget;
import com.example.demo.service.DiversityTargetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/targets")
public class DiversityTargetController {
    private final DiversityTargetService service;
    
    public DiversityTargetController(DiversityTargetService service) {
        this.service = service;
    }
    
    @PostMapping
    public ResponseEntity<DiversityTarget> createTarget(@RequestBody DiversityTarget target) {
        return ResponseEntity.ok(service.createTarget(target));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<DiversityTarget> updateTarget(@PathVariable Long id, @RequestBody DiversityTarget target) {
        return ResponseEntity.ok(service.updateTarget(id, target));
    }
    
    @GetMapping
    public ResponseEntity<List<DiversityTarget>> getAllTargets() {
        return ResponseEntity.ok(service.getAllTargets());
    }
    
    @GetMapping("/year/{year}")
    public ResponseEntity<List<DiversityTarget>> getTargetsByYear(@PathVariable Integer year) {
        return ResponseEntity.ok(service.getTargetsByYear(year));
    }
    
    @PutMapping("/{id}/deactivate")
    public ResponseEntity<DiversityTarget> deactivateTarget(@PathVariable Long id) {
        return ResponseEntity.ok(service.deactivateTarget(id));
    }
}