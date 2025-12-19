package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}
