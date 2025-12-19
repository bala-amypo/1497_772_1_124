package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Entity.PurchaseOrder;
import com.example.demo.service.PurchaseOrderService;

@RestController
@RequestMapping("/api/purchase-orders")
public class PurchaseOrderController {

    @Autowired
    private PurchaseOrderService service;

  
    @PostMapping
    public PurchaseOrder create(@RequestBody PurchaseOrder po) {
        return service.createPurchaseOrder(po);
    }

    
    @PutMapping("/{id}")
    public PurchaseOrder update(@PathVariable Long id,
                                @RequestBody PurchaseOrder po) {
        return service.updatePurchaseOrder(id, po);
    }

  
    @GetMapping("/{id}")
    public PurchaseOrder getById(@PathVariable Long id) {
        return service.getPurchaseOrderById(id);
    }

    @GetMapping
    public List<PurchaseOrder> getAll() {
        return service.getAllPurchaseOrders();
    }

    @GetMapping("/supplier/{supplierId}")
    public List<PurchaseOrder> getBySupplier(@PathVariable Long supplierId) {
        return service.getPurchaseOrdersBySupplier(supplierId);
    }
}
