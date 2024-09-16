package com.exceptional.PlacementManager.controller;

import com.exceptional.PlacementManager.dto.CompanyDto;
import com.exceptional.PlacementManager.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/company")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/insert")
    public ResponseEntity<String> addCompany(@RequestBody CompanyDto companyDto) {
        return adminService.addCompany(companyDto);
    }

    @PostMapping("/show")
    public CompanyDto showCompany(@RequestBody String company_name) throws Exception {
        return adminService.showCompany(company_name);
    }

//    @GetMapping("/show")
//    public CompanyDto showCompany(@RequestParam String company_name) {
//        return adminService.showCompany(company_name);
//    }


}
