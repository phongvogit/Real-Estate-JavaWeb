package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.dto.response.StaffResponseDTO;
import com.laptrinhjavaweb.entity.UserEntity;

import java.util.List;
import java.util.Map;

public interface IUserService {
    UserDTO findOneByUserId(Long id);
    UserDTO findOneByUserNameAndStatus(String name, int status);
    UserDTO save(UserDTO userDTO);
    List<UserDTO> findAll();
    String getHighestAuthority();
    List<StaffResponseDTO> loadStaffByBuildingId(Long buildingId);
    List<StaffResponseDTO> loadStaffByCustomerId(Long customerId);
    Map<Long, String> getStaffMaps();
    Map<String,String> getRoles();
    void delete(Long[] ids);
}
