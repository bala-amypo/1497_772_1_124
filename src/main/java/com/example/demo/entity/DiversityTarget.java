package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "diversity_targets")
public class DiversityTarget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Integer targetYear;
    private Double targetPercentage;
    private Boolean active;
    
    @ManyToOne
    @JoinColumn(name = "classification_id")
    private DiversityClassification classification;
    
    public DiversityTarget() {}
    
    @PrePersist
    public void preSave() {
        this.active = true;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Integer getTargetYear() { return targetYear; }
    public void setTargetYear(Integer targetYear) { this.targetYear = targetYear; }
    
    public Double getTargetPercentage() { return targetPercentage; }
    public void setTargetPercentage(Double targetPercentage) { this.targetPercentage = targetPercentage; }
    
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
    
    public DiversityClassification getClassification() { return classification; }
    public void setClassification(DiversityClassification classification) { this.classification = classification; }
}