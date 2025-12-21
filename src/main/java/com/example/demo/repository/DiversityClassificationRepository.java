package com.example.demo.repository;

import com.example.demo.entity.DiversityClassification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DiversityClassificationRepository extends JpaRepository<DiversityClassification, Long> {
    List<DiversityClassification> findByActiveTrue();
}