package com.example.demo.service;

import java.util.List;

import com.example.demo.model.PurchaseOrder;

public interface PurchaseOrderService {

    PurchaseOrder createPurchaseOrder(PurchaseOrder po);

    PurchaseOrder updatePurchaseOrder(Long id, PurchaseOrder po);

    PurchaseOrder getPurchaseOrderById(Long id);

    List<PurchaseOrder> getAllPurchaseOrders();

    List<PurchaseOrder> getPurchaseOrdersBySupplier(Long supplierId);
}
