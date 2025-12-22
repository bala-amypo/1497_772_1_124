package com.example.demo.controller;
import com.example.demo.entity.DiversityTarget;
import com.example.demo.service.DiversityTargetService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/targets")
public class DiversityTargetController {
    private final DiversityTargetService diversityTargetService;

    public DiversityTargetController(DiversityTargetService diversityTargetService) {
        this.diversityTargetService = diversityTargetService;
    }

    @PostMapping
    public DiversityTarget createTarget(@RequestBody DiversityTarget target) {
        return diversityTargetService.createTarget(target);
    }

    @GetMapping("/year/{year}")
    public List<DiversityTarget> getTargetsByYear(@PathVariable int year) {
        return diversityTargetService.getTargetsByYear(year);
    }

    @PutMapping("/{id}/deactivate")
    public void deactivateTarget(@PathVariable Long id) {
        diversityTargetService.deactivateTarget(id);
    }
}