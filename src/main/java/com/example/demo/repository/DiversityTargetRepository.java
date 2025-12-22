package com.example.demo.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.DiversityTarget;
import java.util.List;

public interface DiversityTargetRepository extends JpaRepository<DiversityTarget, Long> {
    List<DiversityTarget> findByTargetYear(int targetYear);
    List<DiversityTarget> findAll();
}