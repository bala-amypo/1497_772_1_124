package com.example.demo.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.DiversityClassificationEntity;

public interface DiversityClassificationRepository extends JpaRepository<DiversityClassificationEntity, Long> {
    
}
