package com.exceptional.PlacementManager.service;

import com.exceptional.PlacementManager.dto.ApplicationDto;
import com.exceptional.PlacementManager.entity.ApplicationEntity;
import com.exceptional.PlacementManager.entity.OfferEntity;
import com.exceptional.PlacementManager.repository.ApplicationRepository;
import com.exceptional.PlacementManager.repository.OfferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicantService {

    private final OfferRepository offerRepository;
    private final ApplicationRepository applicationRepository;
    private final AuthService authService;

//    public OfferDto fetchJobOffers(String company_name) throws Exception {
//        if(offerRepository.findByName(company_name) == null)
//            throw new Exception("Company not found");
//        return new ModelMapper().map(offerRepository.findByName(company_name), OfferDto.class);
//    }

    public List<OfferEntity> fetchJobOffersByCollegeName(String college) {
        return offerRepository.findByCollegeName(college);
    }

    public ResponseEntity<String> submitApplication(ApplicationDto applicationDto) {
        ApplicationEntity applicationEntity = new ApplicationEntity();
        applicationEntity.setName(applicationDto.getName());
        applicationEntity.setEmail(authService.getCurrentEmail());
        applicationEntity.setAddress(applicationDto.getAddress());
        applicationEntity.setPhone_no(applicationDto.getPhone_no());
        applicationEntity.setDepartment(applicationDto.getDepartment());
        applicationEntity.setDob(applicationDto.getDob());
        applicationEntity.setSex(applicationDto.getSex());
        applicationEntity.setCourse_type(applicationDto.getCourse_type());
        applicationEntity.setPct_10th(applicationDto.getPct_10th());
        applicationEntity.setYop_10th(applicationDto.getYop_10th());
        applicationEntity.setPct_12th(applicationDto.getPct_12th());
        applicationEntity.setYop_12th(applicationDto.getYop_12th());
        applicationEntity.setPhys_marks_12th(applicationDto.getPhys_marks_12th());
        applicationEntity.setPhys_marks_12th(applicationDto.getPhys_marks_12th());
        applicationEntity.setChem_marks_12th(applicationDto.getChem_marks_12th());
        applicationEntity.setMath_marks_12th(applicationDto.getMath_marks_12th());
        applicationEntity.setDiploma_pct_sem1(applicationDto.getDiploma_pct_sem1());
        applicationEntity.setDiploma_pct_sem2(applicationDto.getDiploma_pct_sem2());
        applicationEntity.setDiploma_pct_sem3(applicationDto.getDiploma_pct_sem3());
        applicationEntity.setDiploma_pct_sem4(applicationDto.getDiploma_pct_sem4());
        applicationEntity.setDiploma_pct_sem5(applicationDto.getDiploma_pct_sem5());
        applicationEntity.setDiploma_pct_sem6(applicationDto.getDiploma_pct_sem6());
        applicationEntity.setDiploma_opct(applicationDto.getDiploma_opct());
        applicationEntity.setYop_diploma(applicationDto.getYop_diploma());
        applicationEntity.setBe_sgpa_sem1(applicationDto.getBe_sgpa_sem1());
        applicationEntity.setBe_sgpa_sem2(applicationDto.getBe_sgpa_sem2());
        applicationEntity.setBe_sgpa_sem3(applicationDto.getBe_sgpa_sem3());
        applicationEntity.setBe_sgpa_sem4(applicationDto.getBe_sgpa_sem4());
        applicationEntity.setBe_sgpa_sem5(applicationDto.getBe_sgpa_sem5());
        applicationEntity.setBe_sgpa_sem6(applicationDto.getBe_sgpa_sem6());
        applicationEntity.setBe_cgpa(applicationDto.getBe_cgpa());
        applicationEntity.setBe_cpct(applicationDto.getBe_cpct());
        applicationEntity.setYop_be(applicationDto.getYop_be());
        applicationEntity.setBacklogs(applicationDto.getBacklogs());
        applicationEntity.setResume(applicationDto.getResume());
        applicationEntity.setOffer(offerRepository.findByCompany(applicationDto.getCompany()));
        applicationRepository.save(applicationEntity);
        return ResponseEntity.ok("Your application submitted successfully");
    }

}
