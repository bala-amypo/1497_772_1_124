package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.SpendCategory;

public interface SpendCategoryRepository extends JpaRepository<SpendCategory, Long> {

    boolean existsByName(String name);
}
