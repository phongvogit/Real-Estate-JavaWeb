package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.dto.response.StaffResponseDTO;
import com.laptrinhjavaweb.entity.UserEntity;

import java.util.List;
import java.util.Map;

public interface IUserService {
    UserDTO findOneByUserNameAndStatus(String name, int status);
    List<StaffResponseDTO> loadStaffByBuildingId(Long buildingId);
    List<StaffResponseDTO> loadStaffByCustomerId(Long customerId);
    Map<Long, String> getStaffMaps();
}
