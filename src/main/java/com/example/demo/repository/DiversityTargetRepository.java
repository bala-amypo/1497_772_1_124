package com.example.demo.repository;

import com.example.demo.entity.DiversityTarget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DiversityTargetRepository extends JpaRepository<DiversityTarget, Long> {
    List<DiversityTarget> findByTargetYear(int targetYear);
    List<DiversityTarget> findAll();
}