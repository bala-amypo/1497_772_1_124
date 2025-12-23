package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "diversity_classifications")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiversityClassification {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Classification code is required")
    @Column(unique = true, nullable = false)
    private String code;
    
    private String description;
    
    @Column(nullable = false)
    private Boolean active = true;
    
    @ManyToMany(mappedBy = "diversityClassifications", fetch = FetchType.LAZY)
    private Set<Supplier> suppliers = new HashSet<>();
    
    @OneToMany(mappedBy = "classification", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<DiversityTarget> diversityTargets = new HashSet<>();
    
    @PrePersist
    @PreUpdate
    protected void onSave() {
        if (active == null) {
            active = true;
        }
        if (code != null) {
            code = code.toUpperCase();
        }
    }
    
    public DiversityClassification(String code, String description) {
        this.code = code;
        this.description = description;
        this.active = true;
    }
}