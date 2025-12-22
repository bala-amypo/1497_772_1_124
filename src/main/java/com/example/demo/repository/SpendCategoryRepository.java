package com.example.demo.repository;

import com.example.demo.entity.DiversityClassification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiversityClassificationRepository extends JpaRepository<DiversityClassification, Long> {
}
package com.example.demo.repository;

import com.example.demo.entity.SpendCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpendCategoryRepository extends JpaRepository<SpendCategory, Long> {
}
