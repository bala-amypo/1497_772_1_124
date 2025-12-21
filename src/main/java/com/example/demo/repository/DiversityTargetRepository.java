package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.DiversityTarget;

public interface DiversityTargetRepository
        extends JpaRepository<DiversityTarget, Long> {

    List<DiversityTarget> findByTargetYear(int targetYear);
}
