package com.example.demo.service.impl;

import com.example.demo.entity.Supplier;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.SupplierRepository;
import com.example.demo.service.SupplierService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class SupplierServiceImpl implements SupplierService {
    
    private final SupplierRepository supplierRepository;
    
    public SupplierServiceImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }
    
    @Override
    public Supplier createSupplier(Supplier supplier) {
        if (supplierRepository.existsByEmail(supplier.getEmail())) {
            throw new RuntimeException("Supplier with this email already exists");
        }
        
        if (supplierRepository.findByRegistrationNumber(supplier.getRegistrationNumber()).isPresent()) {
            throw new RuntimeException("Supplier with this registration number already exists");
        }
        
        return supplierRepository.save(supplier);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Supplier getSupplierById(Long id) {
        return supplierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found with id: " + id));
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }
    
    @Override
    public void deactivateSupplier(Long id) {
        Supplier supplier = getSupplierById(id);
        supplier.setIsActive(false);
        supplier.setUpdatedAt(LocalDateTime.now());
        supplierRepository.save(supplier);
    }
}