package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.SpendCategory;

public interface SpendCategoryRepository
        extends JpaRepository<SpendCategory, Long> {

    List<SpendCategory> findByActiveTrue();
}
