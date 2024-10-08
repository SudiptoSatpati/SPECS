package com.exceptional.PlacementManager.service;

import com.exceptional.PlacementManager.dto.ApplicationDto;
import com.exceptional.PlacementManager.dto.OfferDto;
import com.exceptional.PlacementManager.entity.ApplicationEntity;
import com.exceptional.PlacementManager.entity.OfferEntity;
import com.exceptional.PlacementManager.repository.ApplicationRepository;
import com.exceptional.PlacementManager.repository.OfferRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicantService {

    private final OfferRepository offerRepository;
    private final ApplicationRepository applicationRepository;
    private final AuthService authService;
    private final RedisService redisService;


    public List<OfferDto> fetchJobOffersByCollegeName(String college) {

        List<OfferEntity> offers = redisService.get("job_offers_" + college, ArrayList.class);

        if(offers == null) {
            offers = offerRepository.findByCollegeName(college);
            redisService.set("job_offers_" + college, offers, 15);
        }
        
        List<OfferDto> _offers = new ArrayList<>();
        OfferDto offerDto;
        
        for (Object offerObj : offers) {
            OfferEntity offer = new ObjectMapper().convertValue(offerObj, OfferEntity.class);
            offerDto = new OfferDto();
            offerDto.setId(offer.getId());
            offerDto.setCompany(offer.getCompany());
            offerDto.setCriteria(offer.getCriteria());
            offerDto.setOffered_ctc(offer.getOffered_ctc());
            offerDto.setArriving_time(offer.getArriving_time());
            offerDto.setJob_type(offer.getJob_type());
            offerDto.setJob_role(offer.getJob_role());
            offerDto.setAdditional_info(offer.getAdditional_info());
            offerDto.setJob_location(offer.getJob_location());
            offerDto.setJob_description(offer.getJob_description());
            offerDto.setDepartments(offer.getDepartments());
            offerDto.setSelected_candidates(offer.getSelected_candidates());
            offerDto.setLogo(offer.getLogo());
            _offers.add(offerDto);
        }
        return _offers;
    }

    public List<ApplicationDto> fetchMyJobApplications() {
        String email = authService.getCurrentEmail();
        List<ApplicationEntity> applications = redisService.get("job_applications_" + email, ArrayList.class);

        if(applications == null) {
            applications = applicationRepository.findApplicationByEmail(email);
            redisService.set("job_applications_" + email, applications, 15);
        }
        ApplicationDto applicationDto;
        List<ApplicationDto> _applications = new ArrayList<>();

        for (Object app : applications) {
            ApplicationEntity application = new ObjectMapper().convertValue(app, ApplicationEntity.class);
            applicationDto = new ApplicationDto();
            applicationDto.setId(application.getId());
            applicationDto.setName(application.getName());
            applicationDto.setEmail(application.getEmail());
            applicationDto.setAddress(application.getAddress());
            applicationDto.setPhone_no(application.getPhone_no());
            applicationDto.setDepartment(application.getDepartment());
            applicationDto.setDob(application.getDob());
            applicationDto.setSex(application.getSex());
            applicationDto.setCourse_type(application.getCourse_type());
            applicationDto.setPct_10th(application.getPct_10th());
            applicationDto.setYop_10th(application.getYop_10th());
            applicationDto.setPct_12th(application.getPct_12th());
            applicationDto.setYop_12th(application.getYop_12th());
            applicationDto.setPhys_marks_12th(application.getPhys_marks_12th());
            applicationDto.setPhys_marks_12th(application.getPhys_marks_12th());
            applicationDto.setChem_marks_12th(application.getChem_marks_12th());
            applicationDto.setMath_marks_12th(application.getMath_marks_12th());
            applicationDto.setDiploma_pct_sem1(application.getDiploma_pct_sem1());
            applicationDto.setDiploma_pct_sem2(application.getDiploma_pct_sem2());
            applicationDto.setDiploma_pct_sem3(application.getDiploma_pct_sem3());
            applicationDto.setDiploma_pct_sem4(application.getDiploma_pct_sem4());
            applicationDto.setDiploma_pct_sem5(application.getDiploma_pct_sem5());
            applicationDto.setDiploma_pct_sem6(application.getDiploma_pct_sem6());
            applicationDto.setDiploma_opct(application.getDiploma_opct());
            applicationDto.setYop_diploma(application.getYop_diploma());
            applicationDto.setBe_sgpa_sem1(application.getBe_sgpa_sem1());
            applicationDto.setBe_sgpa_sem2(application.getBe_sgpa_sem2());
            applicationDto.setBe_sgpa_sem3(application.getBe_sgpa_sem3());
            applicationDto.setBe_sgpa_sem4(application.getBe_sgpa_sem4());
            applicationDto.setBe_sgpa_sem5(application.getBe_sgpa_sem5());
            applicationDto.setBe_sgpa_sem6(application.getBe_sgpa_sem6());
            applicationDto.setBe_cgpa(application.getBe_cgpa());
            applicationDto.setBe_cpct(application.getBe_cpct());
            applicationDto.setYop_be(application.getYop_be());
            applicationDto.setBacklogs(application.getBacklogs());
            applicationDto.setResume(application.getResume());
            _applications.add(applicationDto);
        }
        return _applications;
    }

    public ResponseEntity<String> submitApplication(ApplicationDto applicationDto) {
        ApplicationEntity applicationEntity = new ApplicationEntity();
        applicationEntity.setUser(authService.getCurrentUser());
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
