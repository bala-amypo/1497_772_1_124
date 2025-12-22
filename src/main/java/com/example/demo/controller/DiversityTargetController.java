package com.example.demo.controller;

import com.example.demo.entity.DiversityTarget;
import com.example.demo.service.DiversityTargetService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/targets")
public class DiversityTargetController {

    private final DiversityTargetService service;

    public DiversityTargetController(DiversityTargetService service) {
        this.service = service;
    }

    @PostMapping
    public DiversityTarget save(@RequestBody DiversityTarget target) {
        return service.save(target);
    }

    @GetMapping("/{id}")
    public DiversityTarget getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping
    public List<DiversityTarget> getAll() {
        return service.getAll();
    }
}
