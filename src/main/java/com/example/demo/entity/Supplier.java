package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "suppliers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Supplier {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Supplier name is required")
    @Column(nullable = false)
    private String name;
    
    @NotBlank(message = "Registration number is required")
    @Column(unique = true, nullable = false)
    private String registrationNumber;
    
    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    @Column(unique = true, nullable = false)
    private String email;
    
    private String phone;
    
    private String address;
    
    @NotNull
    @Column(nullable = false)
    private Boolean isActive = true;
    
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "supplier_classifications",
        joinColumns = @JoinColumn(name = "supplier_id"),
        inverseJoinColumns = @JoinColumn(name = "classification_id")
    )
    private Set<DiversityClassification> diversityClassifications = new HashSet<>();
    
    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<PurchaseOrder> purchaseOrders = new HashSet<>();
    
    @PrePersist
    protected void onCreate() {
        if (isActive == null) {
            isActive = true;
        }
        createdAt = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
    
    public Supplier(String name, String registrationNumber, String email) {
        this.name = name;
        this.registrationNumber = registrationNumber;
        this.email = email;
        this.isActive = true;
    }
    
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}