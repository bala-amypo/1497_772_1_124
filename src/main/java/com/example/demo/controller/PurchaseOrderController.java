package com.example.demo.controller;

import com.example.demo.entity.PurchaseOrder;
import com.example.demo.service.PurchaseOrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/purchase-orders")
public class PurchaseOrderController {
    private final PurchaseOrderService service;
    
    public PurchaseOrderController(PurchaseOrderService service) {
        this.service = service;
    }
    
    @PostMapping
    public ResponseEntity<PurchaseOrder> createPurchaseOrder(@RequestBody PurchaseOrder order) {
        return ResponseEntity.ok(service.createPurchaseOrder(order));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<PurchaseOrder> updatePurchaseOrder(@PathVariable Long id, @RequestBody PurchaseOrder order) {
        return ResponseEntity.ok(service.updatePurchaseOrder(id, order));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<PurchaseOrder> getPurchaseOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getPurchaseOrderById(id));
    }
    
    @GetMapping
    public ResponseEntity<List<PurchaseOrder>> getAllPurchaseOrders() {
        return ResponseEntity.ok(service.getAllPurchaseOrders());
    }
    
    @GetMapping("/supplier/{supplierId}")
    public ResponseEntity<List<PurchaseOrder>> getOrdersBySupplier(@PathVariable Long supplierId) {
        return ResponseEntity.ok(service.getPurchaseOrdersBySupplier(supplierId));
    }
}