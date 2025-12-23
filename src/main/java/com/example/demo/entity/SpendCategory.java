package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "spend_categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpendCategory {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Category name is required")
    @Column(unique = true, nullable = false)
    private String name;
    
    private String description;
    
    @Column(nullable = false)
    private Boolean active = true;
    
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<PurchaseOrder> purchaseOrders = new HashSet<>();
    
    @PrePersist
    @PreUpdate
    protected void onSave() {
        if (active == null) {
            active = true;
        }
    }
    
    public SpendCategory(String name, String description) {
        this.name = name;
        this.description = description;
        this.active = true;
    }
}