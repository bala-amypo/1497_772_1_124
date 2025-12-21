package com.example.demo.service.impl;

import com.example.demo.entity.PurchaseOrder;
import com.example.demo.entity.Supplier;
import com.example.demo.entity.SpendCategory;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.PurchaseOrderRepository;
import com.example.demo.repository.SupplierRepository;
import com.example.demo.repository.SpendCategoryRepository;
import com.example.demo.service.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {
    
    private final PurchaseOrderRepository purchaseOrderRepository;
    private final SupplierRepository supplierRepository;
    private final SpendCategoryRepository spendCategoryRepository;
    
    @Autowired
    public PurchaseOrderServiceImpl(
            PurchaseOrderRepository purchaseOrderRepository,
            SupplierRepository supplierRepository,
            SpendCategoryRepository spendCategoryRepository) {
        this.purchaseOrderRepository = purchaseOrderRepository;
        this.supplierRepository = supplierRepository;
        this.spendCategoryRepository = spendCategoryRepository;
    }
    
    @Override
    @Transactional
    public PurchaseOrder createPurchaseOrder(PurchaseOrder po) {
        // Validate supplier
        Supplier supplier = supplierRepository.findById(po.getSupplier().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found with id: " + po.getSupplier().getId()));
        
        // Validate category
        SpendCategory category = spendCategoryRepository.findById(po.getCategory().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Spend category not found with id: " + po.getCategory().getId()));
        
        // Validate business rules
        if (supplier.getIsActive() != null && !supplier.getIsActive()) {
            throw new BadRequestException("Cannot create purchase order for inactive supplier");
        }
        
        if (category.getActive() != null && !category.getActive()) {
            throw new BadRequestException("Cannot create purchase order for inactive category");
        }
        
        if (po.getAmount() == null || po.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BadRequestException("Purchase order amount must be positive");
        }
        
        if (po.getDateIssued() == null || po.getDateIssued().isAfter(LocalDate.now())) {
            throw new BadRequestException("Purchase order date cannot be in the future");
        }
        
        // Set relationships
        po.setSupplier(supplier);
        po.setCategory(category);
        
        return purchaseOrderRepository.save(po);
    }
    
    @Override
    public List<PurchaseOrder> getPurchaseOrdersBySupplier(Long supplierId) {
        return purchaseOrderRepository.findBySupplier_Id(supplierId);
    }
}