package com.example.demo.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.PurchaseOrder;
import com.example.demo.entity.SpendCategory;
import com.example.demo.entity.Supplier;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.PurchaseOrderRepository;
import com.example.demo.repository.SpendCategoryRepository;
import com.example.demo.repository.SupplierRepository;
import com.example.demo.service.PurchaseOrderService;

@Service
public class PurchaseOrderServiceImpl
        implements PurchaseOrderService {

    private final PurchaseOrderRepository poRepository;
    private final SupplierRepository supplierRepository;
    private final SpendCategoryRepository categoryRepository;

    public PurchaseOrderServiceImpl(
            PurchaseOrderRepository poRepository,
            SupplierRepository supplierRepository,
            SpendCategoryRepository categoryRepository) {

        this.poRepository = poRepository;
        this.supplierRepository = supplierRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public PurchaseOrder createPurchaseOrder(PurchaseOrder po) {

        Supplier supplier = supplierRepository.findById(
                po.getSupplier().getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Supplier not found"));

        SpendCategory category = categoryRepository.findById(
                po.getCategory().getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Category not found"));

        if (!supplier.getIsActive()) {
            throw new BadRequestException("Supplier is inactive");
        }

        if (!category.getActive()) {
            throw new BadRequestException("Category is inactive");
        }

        if (po.getAmount() == null ||
                po.getAmount().signum() <= 0) {
            throw new BadRequestException(
                    "Amount must be positive");
        }

        if (po.getDateIssued() != null &&
                po.getDateIssued().isAfter(LocalDate.now())) {
            throw new BadRequestException(
                    "Date cannot be in the future");
        }

        po.setSupplier(supplier);
        po.setCategory(category);

        return poRepository.save(po);
    }

    @Override
    public List<PurchaseOrder> getPurchaseOrdersBySupplier(Long supplierId) {
        return poRepository.findBySupplier_Id(supplierId);
    }
}
