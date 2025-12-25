package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "diversity_classifications")
public class DiversityClassification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Classification code is required")
    @Column(nullable = false, unique = true)
    private String code;

    private String description;

    @Column(nullable = false)
    private Boolean active = true;

    @ManyToMany(mappedBy = "diversityClassifications")
    private Set<Supplier> suppliers = new HashSet<>();

    @OneToMany(mappedBy = "classification", cascade = CascadeType.ALL)
    private Set<DiversityTarget> diversityTargets = new HashSet<>();

    public DiversityClassification() {
    }

    public DiversityClassification(String code, String description) {
        this.code = code.toUpperCase();
        this.description = description;
    }

    @PrePersist
    @PreUpdate
    protected void preSave() {
        if (code != null) {
            code = code.toUpperCase();
        }
        if (active == null) {
            active = true;
        }
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCode() { return code; }
    public void setCode(String code) { 
        this.code = code != null ? code.toUpperCase() : null; 
    }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }

    public Set<Supplier> getSuppliers() { return suppliers; }
    public void setSuppliers(Set<Supplier> suppliers) { this.suppliers = suppliers; }

    public Set<DiversityTarget> getDiversityTargets() { return diversityTargets; }
    public void setDiversityTargets(Set<DiversityTarget> diversityTargets) { 
        this.diversityTargets = diversityTargets; 
    }
}