package com.exceptional.PlacementManager.repository;

import com.exceptional.PlacementManager.entity.CollegeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollegeRepository extends JpaRepository<CollegeEntity, Long> {
    CollegeEntity findByName(String name);
}
