package com.example.demo.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.SpendCategory;
import java.util.List;

public interface SpendCategoryRepository extends JpaRepository<SpendCategory, Long> {
    List<SpendCategory> findByActiveTrue();
}