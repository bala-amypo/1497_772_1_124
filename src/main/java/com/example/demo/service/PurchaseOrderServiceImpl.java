package com.example.demo.service.impl;

import com.example.demo.entity.PurchaseOrder;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.PurchaseOrderRepository;
import com.example.demo.service.PurchaseOrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

    private final PurchaseOrderRepository repo;

    public PurchaseOrderServiceImpl(PurchaseOrderRepository repo) {
        this.repo = repo;
    }

    public PurchaseOrder save(PurchaseOrder order) {
        return repo.save(order);
    }

    public PurchaseOrder getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Purchase order not found"));
    }

    public List<PurchaseOrder> getAll() {
        return repo.findAll();
    }
}
