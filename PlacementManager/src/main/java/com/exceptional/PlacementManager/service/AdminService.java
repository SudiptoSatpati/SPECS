package com.exceptional.PlacementManager.service;

import com.exceptional.PlacementManager.dto.CompanyDto;
import com.exceptional.PlacementManager.entity.CompanyEntity;
import com.exceptional.PlacementManager.repository.CollegeRepository;
import com.exceptional.PlacementManager.repository.CompanyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AdminService {

    private final CompanyRepository companyRepository;
    private final CollegeRepository collegeRepository;
    private final CollegeService collegeService;


    public AdminService(CompanyRepository companyRepository, CollegeRepository collegeRepository, CollegeService collegeService) {
        this.companyRepository = companyRepository;
        this.collegeRepository = collegeRepository;
        this.collegeService = collegeService;
    }

    public ResponseEntity<String> addCompany(CompanyDto companyDto) {

        if(collegeRepository.findByName(companyDto.getCollege()) == null)
            return ResponseEntity.badRequest().body("College is not registered");
        CompanyEntity companyEntity = new CompanyEntity();
        companyEntity.setName(companyDto.getName());
        companyEntity.setCriteria(companyDto.getCriteria());
        companyEntity.setOffered_ctc(companyDto.getOffered_ctc());
        companyEntity.setArriving_time(companyDto.getArriving_time());
        companyEntity.setJob_type(companyDto.getJob_type());
        companyEntity.setJob_role(companyDto.getJob_role());
        companyEntity.setAdditional_info(companyDto.getAdditional_info());
        companyEntity.setJob_location(companyDto.getJob_location());
        companyEntity.setDepartments(companyDto.getDepartments());
        companyEntity.setSelected_candidates(companyDto.getSelected_candidates());
        companyEntity.setCollege(collegeService.findOrCreateCollege(companyDto.getCollege()));
        companyRepository.save(companyEntity);
        return ResponseEntity.ok("Company registered successfully");
    }

    public CompanyDto showCompany(String company_name) throws Exception {
        if(companyRepository.findByName(company_name) == null)
            throw new Exception("Company not found");
        return new ModelMapper().map(companyRepository.findByName(company_name), CompanyDto.class);
    }

    public List<String> getSelectedCandidates(String company_name) {
        return companyRepository.findByName(company_name).getSelected_candidates();
    }

}
