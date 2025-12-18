package com.example.demo.service;

import java.util.List;

import com.example.demo.Entity.DiversityClassificationEntity;




public interface DiversityClassificationService {

    DiversityClassificationEntity createDiversity(DiversityClassificationEntity diversity);

    List<DiversityClassificationEntity> getAllDiversity();

    DiversityClassificationEntity getDiversityById(Long id);

    DiversityClassificationEntity updateDiversity(Long id, DiversityClassificationEntity diversity);

    void deleteDiversity(Long id);
}
