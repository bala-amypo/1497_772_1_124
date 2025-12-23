package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "diversity_targets")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiversityTarget {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "Target year is required")
    @Column(name = "target_year", nullable = false)
    private Integer targetYear;
    
    @NotNull(message = "Target percentage is required")
    @Min(value = 0, message = "Percentage must be at least 0")
    @Max(value = 100, message = "Percentage must be at most 100")
    @Column(name = "target_percentage", nullable = false)
    private Double targetPercentage;
    
    @Column(nullable = false)
    private Boolean active = true;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "classification_id", nullable = false)
    private DiversityClassification classification;
    
    @PrePersist
    @PreUpdate
    protected void onSave() {
        if (active == null) {
            active = true;
        }
    }
    
    public DiversityTarget(Integer targetYear, Double targetPercentage, 
                          DiversityClassification classification) {
        this.targetYear = targetYear;
        this.targetPercentage = targetPercentage;
        this.classification = classification;
        this.active = true;
    }
}