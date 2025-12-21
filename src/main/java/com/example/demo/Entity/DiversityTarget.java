package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "diversity_targets")
public class DiversityTarget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int targetYear;

    private Double targetPercentage;

    private Boolean active;

    @ManyToOne
    @JoinColumn(name = "classification_id")
    private DiversityClassification classification;

    public DiversityTarget() {
    }

    public DiversityTarget(int targetYear,
                           DiversityClassification classification,
                           Double targetPercentage) {
        this.targetYear = targetYear;
        this.classification = classification;
        this.targetPercentage = targetPercentage;
    }

    @PrePersist
    public void preSave() {
        if (this.active == null) {
            this.active = true;
        }
    }

    // getters & setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getTargetYear() {
        return targetYear;
    }

    public void setTargetYear(int targetYear) {
        this.targetYear = targetYear;
    }

    public Double getTargetPercentage() {
        return targetPercentage;
    }

    public void setTargetPercentage(Double targetPercentage) {
        this.targetPercentage = targetPercentage;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public DiversityClassification getClassification() {
        return classification;
    }

    public void setClassification(DiversityClassification classification) {
        this.classification = classification;
    }
}
