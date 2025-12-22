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
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {
    private final PurchaseOrderRepository purchaseOrderRepository;
    private final SupplierRepository supplierRepository;
    private final SpendCategoryRepository spendCategoryRepository;

    public PurchaseOrderServiceImpl(PurchaseOrderRepository purchaseOrderRepository,
                                    SupplierRepository supplierRepository,
                                    SpendCategoryRepository spendCategoryRepository) {
        this.purchaseOrderRepository = purchaseOrderRepository;
        this.supplierRepository = supplierRepository;
        this.spendCategoryRepository = spendCategoryRepository;
    }

    @Override
    public PurchaseOrder createPurchaseOrder(PurchaseOrder po) {
        if (po.getSupplier() == null || po.getSupplier().getId() == null) {
            throw new BadRequestException("Supplier is required");
        }

        Supplier supplier = supplierRepository.findById(po.getSupplier().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found with id: " + po.getSupplier().getId()));

        if (po.getCategory() == null || po.getCategory().getId() == null) {
            throw new BadRequestException("Category is required");
        }

        SpendCategory category = spendCategoryRepository.findById(po.getCategory().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + po.getCategory().getId()));

        if (!supplier.getIsActive()) {
            throw new BadRequestException("Supplier is inactive");
        }

        if (!category.getActive()) {
            throw new BadRequestException("Category is inactive");
        }

        if (po.getAmount() == null || po.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BadRequestException("Amount must be positive");
        }

        if (po.getDateIssued() != null && po.getDateIssued().isAfter(LocalDate.now())) {
            throw new BadRequestException("Date issued cannot be in the future");
        }

        po.setSupplier(supplier);
        po.setCategory(category);
        return purchaseOrderRepository.save(po);
    }

    @Override
    public List<PurchaseOrder> getPurchaseOrdersBySupplier(Long supplierId) {
        return purchaseOrderRepository.findBySupplier_Id(supplierId);
    }
}