package com.exceptional.PlacementManager.repository;

import com.exceptional.PlacementManager.entity.OfferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<OfferEntity, Long> {
    OfferEntity findByCompany(String company);
    List<OfferEntity> findByCollegeName(String college);
}
