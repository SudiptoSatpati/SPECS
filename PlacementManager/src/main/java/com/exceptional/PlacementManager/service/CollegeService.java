package com.exceptional.PlacementManager.service;

import com.exceptional.PlacementManager.entity.CollegeEntity;
import com.exceptional.PlacementManager.repository.CollegeRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class CollegeService {

    private final CollegeRepository collegeRepository;

    public CollegeService(CollegeRepository collegeRepository) {
        this.collegeRepository = collegeRepository;
    }

    @Transactional
    public CollegeEntity findOrCreateCollege(String collegeName) {
        CollegeEntity collegeEntity = collegeRepository.findByName(collegeName);
        if (collegeEntity == null) {
            CollegeEntity tempCollegeEntity = new CollegeEntity();
            tempCollegeEntity.setName(collegeName);
            collegeEntity = collegeRepository.save(tempCollegeEntity);
        }
        return collegeEntity;
    }

}
