package com.example.demo.service.impl;

import com.example.demo.entity.PurchaseOrder;
import com.example.demo.entity.SpendCategory;
import com.example.demo.entity.Supplier;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.PurchaseOrderRepository;
import com.example.demo.repository.SpendCategoryRepository;
import com.example.demo.repository.SupplierRepository;
import com.example.demo.service.PurchaseOrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

    private final PurchaseOrderRepository purchaseOrderRepository;
    private final SupplierRepository supplierRepository;
    private final SpendCategoryRepository categoryRepository;

    public PurchaseOrderServiceImpl(PurchaseOrderRepository purchaseOrderRepository,
                                   SupplierRepository supplierRepository,
                                   SpendCategoryRepository categoryRepository) {
        this.purchaseOrderRepository = purchaseOrderRepository;
        this.supplierRepository = supplierRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public PurchaseOrder createPurchaseOrder(PurchaseOrder po) {
        // Validate supplier
        Supplier supplier = supplierRepository.findById(po.getSupplier().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found with id: " + po.getSupplier().getId()));
        
        if (Boolean.FALSE.equals(supplier.getIsActive())) {
            throw new BadRequestException("Cannot create purchase order for inactive supplier");
        }
        
        // Validate category
        SpendCategory category = categoryRepository.findById(po.getCategory().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + po.getCategory().getId()));
        
        if (Boolean.FALSE.equals(category.getActive())) {
            throw new BadRequestException("Cannot create purchase order with inactive category");
        }
        
        // Validate amount
        if (po.getAmount() == null || po.getAmount().compareTo(java.math.BigDecimal.ZERO) <= 0) {
            throw new BadRequestException("Purchase order amount must be positive");
        }
        
        // Validate date
        if (po.getDateIssued() == null || po.getDateIssued().isAfter(LocalDate.now())) {
            throw new BadRequestException("Issue date cannot be in the future");
        }
        
        po.setSupplier(supplier);
        po.setCategory(category);
        
        return purchaseOrderRepository.save(po);
    }

    @Override
    public List<PurchaseOrder> getPurchaseOrdersBySupplier(Long supplierId) {
        return purchaseOrderRepository.findBySupplier_Id(supplierId);
    }

    @Override
    public List<PurchaseOrder> getOrdersByCategory(Long categoryId) {
        return purchaseOrderRepository.findByCategory_Id(categoryId);
    }
}