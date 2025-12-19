package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Supplier;
import com.example.demo.repository.SupplierRepository;

@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    SupplierRepository repo;

    @Override
    public Supplier createSupplier(Supplier supplier) {
        return repo.save(supplier);
    }

    @Override
    public Supplier updateSupplier(Long id, Supplier supplier) {
        Supplier existing = repo.findById(id).orElse(null);

        if (existing != null) {
            existing.setName(supplier.getName());
            existing.setRegistrationNumber(supplier.getRegistrationNumber());
            existing.setEmail(supplier.getEmail());
            existing.setPhone(supplier.getPhone());
            existing.setAddress(supplier.getAddress());
            existing.setDiversityClassification(
                    supplier.getDiversityClassification());
            return repo.save(existing);
        }
        return null;
    }

    @Override
    public Supplier getSupplierById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public List<Supplier> getAllSuppliers() {
        return repo.findAll();
    }

    @Override
    public Supplier deactivateSupplier(Long id) {
        Supplier supplier = repo.findById(id).orElse(null);

        if (supplier != null) {
            supplier.setIsActive(false);
            return repo.save(supplier);
        }
        return null;
    }
}
