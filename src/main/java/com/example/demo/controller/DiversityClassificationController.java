package com.example.demo.controller;
import com.example.demo.entity.DiversityClassification;
import com.example.demo.service.DiversityClassificationService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/classifications")
public class DiversityClassificationController {
    private final DiversityClassificationService diversityClassificationService;

    public DiversityClassificationController(DiversityClassificationService diversityClassificationService) {
        this.diversityClassificationService = diversityClassificationService;
    }

    @GetMapping
    public List<DiversityClassification> getAllClassifications() {
        return diversityClassificationService.getAllClassifications();
    }

    @PutMapping("/{id}/deactivate")
    public void deactivateClassification(@PathVariable Long id) {
        diversityClassificationService.deactivateClassification(id);
    }
}