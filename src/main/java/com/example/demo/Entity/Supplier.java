package com.example.demo.entity;

import java.sql.Timestamp;

import jakarta.persistence.*;

@Entity
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(unique = true)
    private String registrationNumber;

    @Column(unique = true, nullable = false)
    private String email;

    private String phone;

    private String address;

    // Simple field instead of relation
    private String diversityClassification;

    private Boolean isActive = true;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    @PrePersist
    public void onCreate() {
        createdAt = new Timestamp(System.currentTimeMillis());
    }

    @PreUpdate
    public void onUpdate() {
        updatedAt = new Timestamp(System.currentTimeMillis());
    }

    public Supplier() {}

    // Getters & Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDiversityClassification() {
        return diversityClassification;
    }

    public void setDiversityClassification(String diversityClassification) {
        this.diversityClassification = diversityClassification;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }
}
