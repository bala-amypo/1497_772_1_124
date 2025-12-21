package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.DiversityTarget;
import com.example.demo.service.DiversityTargetService;

@RestController
@RequestMapping("/targets")
public class DiversityTargetController {

    private final DiversityTargetService service;

    public DiversityTargetController(DiversityTargetService service) {
        this.service = service;
    }

    @PostMapping
    public DiversityTarget create(@RequestBody DiversityTarget target) {
        return service.createTarget(target);
    }

    @GetMapping("/year/{year}")
    public List<DiversityTarget> getByYear(@PathVariable int year) {
        return service.getTargetsByYear(year);
    }

    @PutMapping("/{id}/deactivate")
    public void deactivate(@PathVariable Long id) {
        service.deactivateTarget(id);
    }
}
