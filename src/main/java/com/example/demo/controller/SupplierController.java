package com.example.demo.controller;

import com.example.demo.entity.Supplier;
import com.example.demo.service.SupplierService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/suppliers")
public class SupplierController {

    private final SupplierService service;

    public SupplierController(SupplierService service) {
        this.service = service;
    }

    @PostMapping
    public Supplier save(@RequestBody Supplier supplier) {
        return service.save(supplier);
    }

    @GetMapping("/{id}")
    public Supplier getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping
    public List<Supplier> getAll() {
        return service.getAll();
    }
}
