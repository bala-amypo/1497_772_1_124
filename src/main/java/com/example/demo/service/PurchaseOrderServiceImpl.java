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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
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
    public PurchaseOrder createPurchaseOrder(PurchaseOrder order) {
        // Validate amount
        if (order.getAmount() == null || order.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BadRequestException("Amount must be positive");
        }
        
        // Validate date
        if (order.getDateIssued() == null || order.getDateIssued().isAfter(LocalDate.now())) {
            throw new BadRequestException("Date issued cannot be in the future");
        }
        
        // Get and validate supplier
        Supplier supplier = supplierRepository.findById(order.getSupplier().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found with id: " + order.getSupplier().getId()));
        
        if (!supplier.getIsActive()) {
            throw new BadRequestException("Cannot create purchase order for inactive supplier");
        }
        
        // Get and validate category
        SpendCategory category = categoryRepository.findById(order.getCategory().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + order.getCategory().getId()));
        
        if (!category.getActive()) {
            throw new BadRequestException("Cannot create purchase order for inactive category");
        }
        
        order.setSupplier(supplier);
        order.setCategory(category);
        
        return purchaseOrderRepository.save(order);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<PurchaseOrder> getPurchaseOrdersBySupplier(Long supplierId) {
        return purchaseOrderRepository.findBySupplier_Id(supplierId);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<PurchaseOrder> getOrdersByCategory(Long categoryId) {
        return purchaseOrderRepository.findByCategory_Id(categoryId);
    }
}