package com.exceptional.PlacementManager.service;

import com.exceptional.PlacementManager.entity.DepartmentEntity;
import com.exceptional.PlacementManager.repository.DepartmentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository DepartmentRepository;

    @Transactional
    public DepartmentEntity findOrCreateDepartment(String deptName) {
        DepartmentEntity DepartmentEntity = DepartmentRepository.findByName(deptName);
        if (DepartmentEntity == null) {
            DepartmentEntity tempDepartmentEntity = new DepartmentEntity();
            tempDepartmentEntity.setName(deptName);
            DepartmentEntity = DepartmentRepository.save(tempDepartmentEntity);
        }
        return DepartmentEntity;
    }
    
}
