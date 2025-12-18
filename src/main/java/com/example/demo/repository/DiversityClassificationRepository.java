package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.DiversityClassification;

public interface DiversityClassificationRepository
        extends JpaRepository<DiversityClassification, Long> {

    boolean existsByCode(String code);
}
