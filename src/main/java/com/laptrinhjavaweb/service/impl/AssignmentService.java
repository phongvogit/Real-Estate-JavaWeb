package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.repository.BuildingRepository;
import com.laptrinhjavaweb.repository.UserRepository;
import com.laptrinhjavaweb.service.IAssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssignmentService implements IAssignmentService {

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void saveAssignmentForBuilding(Long buildingId, Long[] staffIds) {
        BuildingEntity buildingEntity = buildingRepository.findOne(buildingId);
        buildingEntity.getStaffs().clear();
        for(Long i : staffIds){
            UserEntity userEntity = userRepository.findOne(i);
            buildingEntity.getStaffs().add(userEntity);
        }
        buildingRepository.save(buildingEntity);
    }
}
