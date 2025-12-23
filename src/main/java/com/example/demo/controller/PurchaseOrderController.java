package com.example.demo.controller;

import com.example.demo.entity.PurchaseOrder;
import com.example.demo.service.PurchaseOrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/purchase-orders")
public class PurchaseOrderController {
    
    private final PurchaseOrderService purchaseOrderService;
    
    public PurchaseOrderController(PurchaseOrderService purchaseOrderService) {
        this.purchaseOrderService = purchaseOrderService;
    }
    
    @PostMapping
    public ResponseEntity<PurchaseOrder> createPurchaseOrder(@RequestBody PurchaseOrder order) {
        PurchaseOrder created = purchaseOrderService.createPurchaseOrder(order);
        return ResponseEntity.ok(created);
    }
    
    @GetMapping("/supplier/{supplierId}")
    public ResponseEntity<List<PurchaseOrder>> getOrdersBySupplier(@PathVariable Long supplierId) {
        List<PurchaseOrder> orders = purchaseOrderService.getPurchaseOrdersBySupplier(supplierId);
        return ResponseEntity.ok(orders);
    }
    
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<PurchaseOrder>> getOrdersByCategory(@PathVariable Long categoryId) {
        List<PurchaseOrder> orders = purchaseOrderService.getOrdersByCategory(categoryId);
        return ResponseEntity.ok(orders);
    }
}