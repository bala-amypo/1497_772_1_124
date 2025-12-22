package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "diversity_classifications")
public class DiversityClassification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String code;
    
    private String description;
    
    private Boolean active;
    
    @ManyToMany(mappedBy = "diversityClassifications")
     @JsonIgnore 
    private Set<Supplier> suppliers = new HashSet<>();
    
    @OneToMany(mappedBy = "classification", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<DiversityTarget> diversityTargets = new HashSet<>();
    
    @PrePersist
    @PreUpdate
    protected void onSave() {
        if (code != null) {
            code = code.toUpperCase();
        }
        if (active == null) {
            active = true;
        }
    }
    
    public DiversityClassification() {}
    
    public DiversityClassification(String code, String description) {
        this.code = code;
        this.description = description;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
    
    public Set<Supplier> getSuppliers() { return suppliers; }
    public void setSuppliers(Set<Supplier> suppliers) { this.suppliers = suppliers; }
    
    public Set<DiversityTarget> getDiversityTargets() { return diversityTargets; }
    public void setDiversityTargets(Set<DiversityTarget> diversityTargets) { this.diversityTargets = diversityTargets; }
}