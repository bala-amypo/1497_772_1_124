package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.DiversityClassification;

public interface DiversityClassificationRepository
        extends JpaRepository<DiversityClassification, Long> {

    List<DiversityClassification> findByActiveTrue();
}
