package com.example.demo.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class DiversityTarget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer targetYear;

    @ManyToOne
    private DiversityClassification classification;

    private Double targetPercentage;

    private Boolean active = true;

    public DiversityTarget() {
    }

    public DiversityTarget(Long id, Integer targetYear,
                           DiversityClassification classification,
                           Double targetPercentage, Boolean active) {
        this.id = id;
        this.targetYear = targetYear;
        this.classification = classification;
        this.targetPercentage = targetPercentage;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTargetYear() {
        return targetYear;
    }

    public void setTargetYear(Integer targetYear) {
        this.targetYear = targetYear;
    }

    public DiversityClassification getClassification() {
        return classification;
    }

    public void setClassification(DiversityClassification classification) {
        this.classification = classification;
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
}
