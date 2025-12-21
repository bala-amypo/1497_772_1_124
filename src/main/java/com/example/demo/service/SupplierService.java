package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.Supplier;

public interface SupplierService {

    Supplier createSupplier(Supplier supplier);

    Supplier getSupplierById(Long id);

    List<Supplier> getAllSuppliers();

    void deactivateSupplier(Long id);
}
