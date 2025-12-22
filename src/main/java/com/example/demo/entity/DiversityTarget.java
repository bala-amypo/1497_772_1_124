package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "diversity_targets")
@JsonIgnoreProperties({"classification.suppliers", "classification.diversityTargets"}) // Add this
public class DiversityTarget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "target_year", nullable = false)
    private Integer targetYear;
    
    @Column(name = "target_percentage", nullable = false)
    private Double targetPercentage;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "classification_id", nullable = false)
    private DiversityClassification classification;
    
    private Boolean active;
    
  
    @PrePersist
    @PreUpdate
    protected void onSave() {
        if (active == null) {
            active = true;
        }
    }
    
    public DiversityTarget() {}
    
    public DiversityTarget(Integer targetYear, DiversityClassification classification, Double targetPercentage) {
        this.targetYear = targetYear;
        this.classification = classification;
        this.targetPercentage = targetPercentage;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Integer getTargetYear() { return targetYear; }
    public void setTargetYear(Integer targetYear) { this.targetYear = targetYear; }
    
    public Double getTargetPercentage() { return targetPercentage; }
    public void setTargetPercentage(Double targetPercentage) { this.targetPercentage = targetPercentage; }
    
    public DiversityClassification getClassification() { return classification; }
    public void setClassification(DiversityClassification classification) { this.classification = classification; }
    
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}