package com.exceptional.PlacementManager.repository;

import com.exceptional.PlacementManager.entity.ApplicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository extends JpaRepository<ApplicationEntity, Long> {
//    ApplicationEntity findApplicationByCompany(String offer);
}
