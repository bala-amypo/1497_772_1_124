package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.DiversityClassificationEntity;
import com.example.demo.repository.DiversityClassificationRepository;

@Service
public class DiversityClassificationServiceImpl
        implements DiversityClassificationService {

    @Autowired
    private DiversityClassificationRepository diversityRepo;

    @Override
    public DiversityClassificationEntity createDiversity(
            DiversityClassificationEntity diversity) {
        return diversityRepo.save(diversity);
    }

   
    @Override
    public List<DiversityClassificationEntity> getAllDiversity() {
        return diversityRepo.findAll();
    }

  
    @Override
    public DiversityClassificationEntity getDiversityById(Long id) {
        return diversityRepo.findById(id).orElse(null);
    }

  
    @Override
    public DiversityClassificationEntity updateDiversity(
            Long id, DiversityClassificationEntity diversity) {

        DiversityClassificationEntity existing =
                diversityRepo.findById(id).orElse(null);

        if (existing != null) {
            
            existing.setName(diversity.getName());
            existing.setDescription(diversity.getDescription());

            return diversityRepo.save(existing);
        }

        return null;
    }

   
    @Override
    public void deleteDiversity(Long id) {
        diversityRepo.deleteById(id);
    }
}
