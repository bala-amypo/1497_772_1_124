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
    
    @GetMapping("/supplier/{supplierId}")
    public ResponseEntity<List<PurchaseOrder>> getOrdersBySupplier(@PathVariable Long supplierId) {
        return ResponseEntity.ok(service.getPurchaseOrdersBySupplier(supplierId));
    }
    
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<PurchaseOrder>> getOrdersByCategory(@PathVariable Long categoryId) {
        return ResponseEntity.ok(service.getPurchaseOrdersByCategory(categoryId));
    }
}