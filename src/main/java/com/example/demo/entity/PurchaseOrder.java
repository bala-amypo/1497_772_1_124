package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "purchase_orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseOrder {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "PO number is required")
    @Column(unique = true, nullable = false)
    private String poNumber;
    
    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.01", message = "Amount must be greater than 0")
    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal amount;
    
    @NotNull(message = "Date issued is required")
    @Column(name = "date_issued", nullable = false)
    private LocalDate dateIssued;
    
    @Column(name = "approved_by")
    private String approvedBy;
    
    private String notes;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id", nullable = false)
    private Supplier supplier;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private SpendCategory category;
    
    public PurchaseOrder(String poNumber, BigDecimal amount, LocalDate dateIssued, 
                        Supplier supplier, SpendCategory category) {
        this.poNumber = poNumber;
        this.amount = amount;
        this.dateIssued = dateIssued;
        this.supplier = supplier;
        this.category = category;
    }
}