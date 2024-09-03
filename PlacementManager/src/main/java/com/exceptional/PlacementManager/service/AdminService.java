package com.exceptional.PlacementManager.service;

import com.exceptional.PlacementManager.dto.CompanyDto;
import com.exceptional.PlacementManager.entity.CompanyEntity;
import com.exceptional.PlacementManager.repository.CompanyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    private final CompanyRepository companyRepository;


    public AdminService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public CompanyDto addCompany(CompanyDto companyDto) {

        return new ModelMapper().map(companyRepository.save(
                new ModelMapper().map(companyDto, CompanyEntity.class)), CompanyDto.class);
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
