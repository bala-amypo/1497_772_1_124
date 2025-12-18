package com.example.demo.Entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class DiversityClassificationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   
    private String code;

    private String description;
    private boolean active = true;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }
    public DiversityClassificationEntity(Long id, String code, String description, boolean active) {
        this.id = id;
        this.code = code;
        this.description = description;
        this.active = active;
    }
    public DiversityClassificationEntity() {
    }
    public Object getName() {
       
        throw new UnsupportedOperationException("Unimplemented method 'getName'");
    }
    public void setName(Object name) {
       
        throw new UnsupportedOperationException("Unimplemented method 'setName'");
    }
    
}
