package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "diversity_targets")
public class DiversityTarget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Target year is required")
    @Column(name = "target_year", nullable = false)
    private Integer targetYear;

    @NotNull(message = "Target percentage is required")
    @Min(value = 0, message = "Percentage must be between 0 and 100")
    @Max(value = 100, message = "Percentage must be between 0 and 100")
    @Column(name = "target_percentage", nullable = false)
    private Double targetPercentage;

    @ManyToOne
    @JoinColumn(name = "classification_id", nullable = false)
    private DiversityClassification classification;

    @Column(nullable = false)
    private Boolean active = true;

    public DiversityTarget() {
    }

    public DiversityTarget(Integer targetYear, Double targetPercentage, DiversityClassification classification) {
        this.targetYear = targetYear;
        this.targetPercentage = targetPercentage;
        this.classification = classification;
    }

    @PrePersist
    @PreUpdate
    protected void preSave() {
        if (active == null) {
            active = true;
        }
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Integer getTargetYear() { return targetYear; }
    public void setTargetYear(Integer targetYear) { this.targetYear = targetYear; }

    public Double getTargetPercentage() { return targetPercentage; }
    public void setTargetPercentage(Double targetPercentage) { this.targetPercentage = targetPercentage; }

    public DiversityClassification getClassification() { return classification; }
    public void setClassification(DiversityClassification classification) { 
        this.classification = classification; 
    }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}