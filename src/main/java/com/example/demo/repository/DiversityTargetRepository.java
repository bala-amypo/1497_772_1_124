package com.example.demo.repository;

import com.example.demo.entity.DiversityTarget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface DiversityTargetRepository extends JpaRepository<DiversityTarget, Long> {
    List<DiversityTarget> findByTargetYear(Integer targetYear);
    List<DiversityTarget> findAll();
    Optional<DiversityTarget> findByTargetYearAndClassificationId(Integer year, Long classificationId);
    List<DiversityTarget> findByActiveTrue();
}