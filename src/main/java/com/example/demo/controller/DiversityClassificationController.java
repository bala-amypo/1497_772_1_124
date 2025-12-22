package com.example.demo.controller;

import com.example.demo.entity.DiversityClassification;
import com.example.demo.service.DiversityClassificationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/classifications")
public class DiversityClassificationController {

    private final DiversityClassificationService service;

    public DiversityClassificationController(
            DiversityClassificationService service) {
        this.service = service;
    }

    @PostMapping
    public DiversityClassification save(@RequestBody DiversityClassification c) {
        return service.save(c);
    }

    @GetMapping("/{id}")
    public DiversityClassification getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping
    public List<DiversityClassification> getAll() {
        return service.getAll();
    }
}
