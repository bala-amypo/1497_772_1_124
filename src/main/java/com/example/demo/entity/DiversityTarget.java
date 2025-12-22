package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class DiversityTarget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int targetYear;
    private Double targetPercentage;
    private Boolean active = true;

    public DiversityTarget() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public int getTargetYear() { return targetYear; }
    public void setTargetYear(int targetYear) { this.targetYear = targetYear; }

    public Double getTargetPercentage() { return targetPercentage; }
    public void setTargetPercentage(Double targetPercentage) {
        this.targetPercentage = targetPercentage;
    }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}
