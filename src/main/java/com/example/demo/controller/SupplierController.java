package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.entity.Supplier;
import com.example.demo.service.SupplierService;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    @Autowired
    SupplierService service;

    @PostMapping
    public Supplier create(@RequestBody Supplier supplier) {
        return service.createSupplier(supplier);
    }

    @PutMapping("/{id}")
    public Supplier update(@PathVariable Long id,
                           @RequestBody Supplier supplier) {
        return service.updateSupplier(id, supplier);
    }

    @GetMapping("/{id}")
    public Supplier getById(@PathVariable Long id) {
        return service.getSupplierById(id);
    }

    @GetMapping
    public List<Supplier> getAll() {
        return service.getAllSuppliers();
    }

    @PutMapping("/{id}/deactivate")
    public Supplier deactivate(@PathVariable Long id) {
        return service.deactivateSupplier(id);
    }
}
