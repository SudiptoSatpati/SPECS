package com.exceptional.PlacementManager.controller;

import com.exceptional.PlacementManager.dto.ApplicationDto;
import com.exceptional.PlacementManager.dto.OfferDto;
import com.exceptional.PlacementManager.entity.ApplicationEntity;
import com.exceptional.PlacementManager.entity.OfferEntity;
import com.exceptional.PlacementManager.service.ApplicantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ApplicantController {

    private final ApplicantService applicantService;

    @PostMapping("/job-offers")
    public List<OfferDto> getJobOffersByCollegeName(@RequestBody String college) {
        return applicantService.fetchJobOffersByCollegeName(college);
    }

    @PostMapping("/application-submit")
    public ResponseEntity<String> submitApplication(@RequestBody ApplicationDto applicationDto) {
        return applicantService.submitApplication(applicationDto);
    }

    @PostMapping("/my-job-applications")
    public List<ApplicationDto> fetchApplicationsByEmail() {
        return applicantService.fetchMyJobApplications();
    }

}
