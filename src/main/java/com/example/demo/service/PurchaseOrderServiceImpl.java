package com.example.demo.service.impl;

import com.example.demo.entity.PurchaseOrder;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.PurchaseOrderRepository;
import com.example.demo.service.PurchaseOrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseOrderServiceImpl
        implements PurchaseOrderService {

    private final PurchaseOrderRepository repository;

    public PurchaseOrderServiceImpl(PurchaseOrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public PurchaseOrder save(PurchaseOrder order) {
        return repository.save(order);
    }

    @Override
    public PurchaseOrder getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("PurchaseOrder not found"));
    }

    @Override
    public List<PurchaseOrder> getAll() {
        return repository.findAll();
    }
}
