package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.constant.SystemConstant;
import com.laptrinhjavaweb.converter.UserConverter;
import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.dto.response.StaffResponseDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.CustomerEntity;
import com.laptrinhjavaweb.entity.RoleEntity;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.repository.BuildingRepository;
import com.laptrinhjavaweb.repository.CustomerRepository;
import com.laptrinhjavaweb.repository.RoleRepository;
import com.laptrinhjavaweb.repository.UserRepository;
import com.laptrinhjavaweb.security.utils.SecurityUtils;
import com.laptrinhjavaweb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDTO findOneByUserId(Long id) {
        return userConverter.convertToDto(userRepository.findOne(id));
    }

    @Override
    public UserDTO findOneByUserNameAndStatus(String name, int status) {
        return userConverter.convertToDto(userRepository.findOneByUserNameAndStatus(name, status));
    }

    @Override
    @Transactional
    public UserDTO save(UserDTO userDTO) {
        UserEntity newUser = userConverter.convertToEntity(userDTO);
        if(userDTO.getId() != null){
            UserEntity oldUser = userRepository.findOne(userDTO.getId());
            if(userDTO.getPassword() == null){
                newUser.setPassword(oldUser.getPassword());
            }else{
                newUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            }
            newUser.setCreatedBy(oldUser.getCreatedBy());
            newUser.setCreatedDate(oldUser.getCreatedDate());
            newUser.setRoles(oldUser.getRoles());
        }else{
            RoleEntity role = roleRepository.findOneByCode(userDTO.getRole());
            newUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            newUser.setRoles(Stream.of(role).collect(Collectors.toList()));
        }
        return userConverter.convertToDto(userRepository.save(newUser));
    }

    @Override
    public List<UserDTO> findAll() {
        List<UserDTO> result = new ArrayList<>();
        for(UserEntity user : userRepository.findAll()){
            UserDTO userDTO = userConverter.convertToDto(user);
            result.add(userDTO);
        }
        return result;
    }

    @Override
    public String getHighestAuthority() {
        for(String authority : SecurityUtils.getAuthorities()){
            if(authority.equals(SystemConstant.MANAGER_ROLE)){
                return "MANAGER";
            }
        }
        return "STAFF";
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

    @Override
    public Map<String, String> getRoles() {
        Map<String, String> result = new HashMap<>();
        List<RoleEntity> roles = roleRepository.findAll();
        for(RoleEntity item : roles){
            result.put(item.getCode(), item.getName());
        }
        return result;
    }

    @Override
    @Transactional
    public void delete(Long[] ids) {
        for(Long id : ids){
            UserEntity user = userRepository.findOne(id);
            for(RoleEntity role : user.getRoles()){
                role.getUsers().remove(user);
            }
            for(BuildingEntity buildingPriority : user.getBuildingPriorities()){
                buildingPriority.getUsers().remove(user);
            }
            for(BuildingEntity building : user.getBuildings()){
                building.getStaffs().remove(user);
            }
            for(CustomerEntity customer : user.getCustomers()){
                customer.getUsers().remove(user);
            }
            userRepository.delete(user);
        }
    }
}
