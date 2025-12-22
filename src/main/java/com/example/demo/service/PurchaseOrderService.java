package com.example.demo.service;

import com.example.demo.entity.PurchaseOrder;
import java.util.List;

public interface PurchaseOrderService {

    PurchaseOrder save(PurchaseOrder order);

    PurchaseOrder getById(Long id);

    List<PurchaseOrder> getAll();
}
