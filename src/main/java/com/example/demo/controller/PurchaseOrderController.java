package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.PurchaseOrder;
import com.example.demo.service.PurchaseOrderService;

@RestController
@RequestMapping("/api/purchase-orders")
public class PurchaseOrderController {

    @Autowired
    private PurchaseOrderService service;

    // POST /api/purchase-orders
    @PostMapping
    public PurchaseOrder create(@RequestBody PurchaseOrder po) {
        return service.createPurchaseOrder(po);
    }

    // PUT /api/purchase-orders/{id}
    @PutMapping("/{id}")
    public PurchaseOrder update(@PathVariable Long id,
                                @RequestBody PurchaseOrder po) {
        return service.updatePurchaseOrder(id, po);
    }

    // GET /api/purchase-orders/{id}
    @GetMapping("/{id}")
    public PurchaseOrder getById(@PathVariable Long id) {
        return service.getPurchaseOrderById(id);
    }

    // GET /api/purchase-orders
    @GetMapping
    public List<PurchaseOrder> getAll() {
        return service.getAllPurchaseOrders();
    }

    // GET /api/purchase-orders/supplier/{supplierId}
    @GetMapping("/supplier/{supplierId}")
    public List<PurchaseOrder> getBySupplier(@PathVariable Long supplierId) {
        return service.getPurchaseOrdersBySupplier(supplierId);
    }
}
