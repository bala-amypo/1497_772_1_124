package com.example.demo.service;

import com.example.demo.entity.Supplier;
import java.util.List;

public interface SupplierService {
    Supplier save(Supplier supplier);
    Supplier getById(Long id);
    List<Supplier> getAll();
}
