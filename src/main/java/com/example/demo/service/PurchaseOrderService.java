package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.PurchaseOrder;

public interface PurchaseOrderService {

    PurchaseOrder createPurchaseOrder(PurchaseOrder po);

    List<PurchaseOrder> getPurchaseOrdersBySupplier(Long supplierId);
}
