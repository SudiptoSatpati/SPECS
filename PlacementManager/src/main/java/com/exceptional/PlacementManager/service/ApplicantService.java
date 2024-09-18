package com.exceptional.PlacementManager.service;

import com.exceptional.PlacementManager.entity.OfferEntity;
import com.exceptional.PlacementManager.repository.OfferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicantService {

    private final OfferRepository offerRepository;

//    public OfferDto fetchJobOffers(String company_name) throws Exception {
//        if(offerRepository.findByName(company_name) == null)
//            throw new Exception("Company not found");
//        return new ModelMapper().map(offerRepository.findByName(company_name), OfferDto.class);
//    }

    public List<OfferEntity> fetchJobOffersByCollegeName(String college) {
        return offerRepository.findByCollegeName(college);
    }



}
