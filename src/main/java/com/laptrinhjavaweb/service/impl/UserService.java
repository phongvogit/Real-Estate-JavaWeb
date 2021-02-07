package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.converter.UserConverter;
import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.dto.response.StaffResponseDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.CustomerEntity;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.repository.BuildingRepository;
import com.laptrinhjavaweb.repository.CustomerRepository;
import com.laptrinhjavaweb.repository.UserRepository;
import com.laptrinhjavaweb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;

    @Override
    public UserDTO findOneByUserNameAndStatus(String name, int status) {
        return userConverter.convertToDto(userRepository.findOneByUserNameAndStatus(name, status));
    }


    @Override
    public List<StaffResponseDTO> loadStaffByBuildingId(Long buildingId) {
        List<UserEntity> staffs = userRepository.findByStatusAndRoles_Code(1,"STAFF");
        List<StaffResponseDTO> results = new ArrayList<>();
        for(UserEntity staff : staffs){
            StaffResponseDTO responseDTO = userConverter.convertToStaffResponseDto(staff);
            if(userRepository.existsByIdAndBuildings_Id(staff.getId(), buildingId)){
                responseDTO.setCheck("checked");
            }
            results.add(responseDTO);
        }
        return results;
    }

    @Override
    public List<StaffResponseDTO> loadStaffByCustomerId(Long customerId) {
        List<UserEntity> staffs = userRepository.findByStatusAndRoles_Code(1,"STAFF");
        List<StaffResponseDTO> results = new ArrayList<>();
        for(UserEntity staff : staffs){
            StaffResponseDTO responseDTO = userConverter.convertToStaffResponseDto(staff);
            if(userRepository.existsByIdAndCustomers_Id(staff.getId(), customerId)){
                responseDTO.setCheck("checked");
            }
            results.add(responseDTO);
        }
        return results;
    }

    @Override
    public Map<Long, String> getStaffMaps() {
        Map<Long, String> result = new HashMap<>();
        List<UserEntity> staffs = userRepository.findByStatusAndRoles_Code(1,"STAFF");
        for(UserEntity item : staffs){
            result.put(item.getId(), item.getFullName());
        }
        return result;
    }
}
