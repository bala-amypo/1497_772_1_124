package com.example.demo.repository;

import com.example.demo.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    Optional<Supplier> findByRegistrationNumber(String registrationNumber);
    Optional<Supplier> findByEmail(String email);
    List<Supplier> findByIsActiveTrue();
    boolean existsByEmail(String email);
}