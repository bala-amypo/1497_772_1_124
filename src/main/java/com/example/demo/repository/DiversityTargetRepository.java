package com.example.demo.repository;

import com.example.demo.entity.DiversityTarget;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface DiversityTargetRepository extends JpaRepository<DiversityTarget, Long> {
    Optional<DiversityTarget> findByTargetYearAndClassificationId(Integer year, Long classificationId);
    List<DiversityTarget> findByActiveTrue();
    List<DiversityTarget> findByTargetYear(Integer targetYear);
}