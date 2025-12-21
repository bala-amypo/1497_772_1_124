package com.example.demo.repository;

import com.example.demo.entity.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {
    List<PurchaseOrder> findBySupplier_Id(Long supplierId);
    List<PurchaseOrder> findAll();
}