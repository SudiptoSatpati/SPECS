package com.exceptional.PlacementManager.controller;

import com.exceptional.PlacementManager.dto.ApplicationDto;
import com.exceptional.PlacementManager.dto.OfferDto;
import com.exceptional.PlacementManager.entity.ApplicationEntity;
import com.exceptional.PlacementManager.entity.OfferEntity;
import com.exceptional.PlacementManager.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/job-offers")
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/insert")
    public ResponseEntity<String> addCompany(@RequestBody OfferDto offerDto) {
        return adminService.addJobOffer(offerDto);
    }

    @PostMapping("/fetch-applications")
    public List<ApplicationDto> fetchApplicationsByCompany(@RequestBody String company) {
        return adminService.fetchJobApplicationsByCompany(company);
    }

}
