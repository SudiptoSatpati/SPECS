package com.exceptional.PlacementManager.controller;

import com.exceptional.PlacementManager.dto.OfferDto;
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


}
