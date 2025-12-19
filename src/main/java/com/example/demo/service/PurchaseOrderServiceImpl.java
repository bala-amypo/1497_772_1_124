package com.example.demo.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.PurchaseOrder;
import com.example.demo.repository.PurchaseOrderRepository;

@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

    @Autowired
    private PurchaseOrderRepository repo;

    @Override
    public PurchaseOrder createPurchaseOrder(PurchaseOrder po) {

        if (po.getAmount() == null || po.getAmount().doubleValue() <= 0) {
            throw new RuntimeException("Amount must be greater than 0");
        }

        if (po.getDateIssued() != null && po.getDateIssued().after(new Date())) {
            throw new RuntimeException("Date cannot be in the future");
        }

        return repo.save(po);
    }

    @Override
    public PurchaseOrder updatePurchaseOrder(Long id, PurchaseOrder po) {
        PurchaseOrder existing = repo.findById(id).orElse(null);

        if (existing == null) {
            return null;
        }

        existing.setPoNumber(po.getPoNumber());
        existing.setSupplier(po.getSupplier());
        existing.setCategory(po.getCategory());
        existing.setAmount(po.getAmount());
        existing.setDateIssued(po.getDateIssued());
        existing.setApprovedBy(po.getApprovedBy());
        existing.setNotes(po.getNotes());

        return repo.save(existing);
    }

    @Override
    public PurchaseOrder getPurchaseOrderById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public List<PurchaseOrder> getAllPurchaseOrders() {
        return repo.findAll();
    }

    @Override
    public List<PurchaseOrder> getPurchaseOrdersBySupplier(Long supplierId) {
        return repo.findBySupplierId(supplierId);
    }
}
