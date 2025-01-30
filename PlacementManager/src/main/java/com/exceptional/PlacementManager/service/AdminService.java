package com.exceptional.PlacementManager.service;

import com.exceptional.PlacementManager.dto.ApplicationDto;
import com.exceptional.PlacementManager.dto.OfferDto;
import com.exceptional.PlacementManager.entity.ApplicationEntity;
import com.exceptional.PlacementManager.entity.OfferEntity;
import com.exceptional.PlacementManager.repository.ApplicationRepository;
import com.exceptional.PlacementManager.repository.CollegeRepository;
import com.exceptional.PlacementManager.repository.OfferRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final OfferRepository offerRepository;
    private final CollegeRepository collegeRepository;
    private final CollegeService collegeService;
    private final AuthService authService;
    private final RedisService redisService;
    private final ApplicationRepository applicationRepository;

    public ResponseEntity<String> addJobOffer(OfferDto offerDto) {

        String college = authService.getCurrentUser().getCollege().getName();
        if(collegeRepository.findByName(college) == null)
            return ResponseEntity.badRequest().body("College is not registered");
        OfferEntity offerEntity = new OfferEntity();
        offerEntity.setCompany(offerDto.getCompany());
        offerEntity.setOffered_ctc(offerDto.getOffered_ctc());
        offerEntity.setJob_type(offerDto.getJob_type());
        offerEntity.setJob_role(offerDto.getJob_role());
        offerEntity.setAdditional_info(new ArrayList<>(Arrays.asList(offerDto.getAdditional_info().split("\\R"))));
        offerEntity.setJob_location(offerDto.getJob_location());
        offerEntity.setJob_description(new ArrayList<>(Arrays.asList(offerDto.getJob_description().split("\\R"))));
        offerEntity.setQualifications(new ArrayList<>(Arrays.asList(offerDto.getQualifications().split("\\R"))));
        offerEntity.setRequirements(new ArrayList<>(Arrays.asList(offerDto.getRequirements().split("\\R"))));
        offerEntity.setResponsibilities(new ArrayList<>(Arrays.asList(offerDto.getResponsibilities().split("\\R"))));
        offerEntity.setSalary_details(new ArrayList<>(Arrays.asList(offerDto.getSalary_details().split("\\R"))));
        offerEntity.setExpires(offerDto.getExpires());
        offerEntity.setDepartments(offerDto.getDepartments());
        offerEntity.setPosted(offerDto.getPosted());
        offerEntity.setSelected_candidates(offerDto.getSelected_candidates());
        offerEntity.setCollege(collegeService.findOrCreateCollege(college));
        offerEntity.setLogo(offerDto.getLogo());
        offerRepository.save(offerEntity);
        return ResponseEntity.ok("Company registered successfully");
    }

    public List<String> getSelectedCandidates(String company) {
        return offerRepository.findByCompany(company).getSelected_candidates();
    }

    public List<ApplicationDto> fetchJobApplicationsByCompany(String company) {

        List<ApplicationEntity> applications = redisService.get("job_applications_" + company, ArrayList.class);

        if(applications == null) {
            applications = applicationRepository.findApplicationByOfferCompany(company);
            redisService.set("job_applications_" + company, applications, 15);
        }

        ApplicationDto applicationDto;
        List<ApplicationDto> _applications = new ArrayList<>();

        for (Object app : applications) {
            ApplicationEntity application = new ObjectMapper().convertValue(app, ApplicationEntity.class);
            applicationDto = new ApplicationDto();
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

}
