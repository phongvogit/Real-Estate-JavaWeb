package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.Enums.BuildingTypeEnum;
import com.laptrinhjavaweb.Enums.DistrictEnum;
import com.laptrinhjavaweb.builder.BuildingBuilder;
import com.laptrinhjavaweb.constant.SystemConstant;
import com.laptrinhjavaweb.converter.BuildingConverter;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.PageDTO;
import com.laptrinhjavaweb.dto.response.BuildingPageResponseDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.RentAreaEntity;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.page.PageRequest;
import com.laptrinhjavaweb.repository.RentAreaRepository;
import com.laptrinhjavaweb.repository.BuildingRepository;
import com.laptrinhjavaweb.repository.UserRepository;
import com.laptrinhjavaweb.security.utils.SecurityUtils;
import com.laptrinhjavaweb.service.IBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BuildingService implements IBuildingService {

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private RentAreaRepository rentAreaRepository;

    @Autowired
    private BuildingConverter buildingConverter;

    @Autowired
    private UserRepository userRepository;


    @Override
    @Transactional
    public BuildingDTO saveNewBuilding(BuildingDTO model ) {
        BuildingEntity buildingEntity = buildingConverter.convertToEntity(model);
        if(model.getId() != null){
            BuildingEntity existBuilding = buildingRepository.findOne(model.getId());
            buildingEntity.setCreatedBy(existBuilding.getCreatedBy());
            buildingEntity.setCreatedDate(existBuilding.getCreatedDate());
            buildingEntity.setStaffs(existBuilding.getStaffs());
        }
        //BuildingTypes
        if(model.getBuildingTypes() != null && model.getBuildingTypes().length != 0)
            buildingEntity.setType(String.join(",", model.getBuildingTypes()));
        buildingEntity = saveRentArea( buildingEntity, model);
        //Saved Building
        BuildingDTO result = buildingConverter.convertToDto(buildingRepository.save(buildingEntity));
        return result;
    }

    private BuildingEntity saveRentArea(BuildingEntity buildingEntity, BuildingDTO model){
        if(model.getRentArea() != null && !model.getRentArea().equals("")){
            //Removed Old RentAreas
            if(model.getCheckUpdate() == true)
                rentAreaRepository.deleteBuildingId(buildingEntity.getId());

            List<String> rentAreas = Arrays.asList(model.getRentArea().split(","));
            for(String i : rentAreas){
                RentAreaEntity rentAreaEntity = new RentAreaEntity();
                rentAreaEntity.setBuilding(buildingEntity);
                try{
                    rentAreaEntity.setValue(Integer.parseInt(i));
                    buildingEntity.getRentAreas().add(rentAreaEntity);
                }catch (NumberFormatException e){
                    System.out.println(i + "is not valid number");
                }
            }
        }
        return buildingEntity;
    }

    @Override
    @Transactional
    public void deleteBuilding(Long[] ids) {
        for(Long id : ids){
            BuildingEntity building = buildingRepository.findOne(id);
            rentAreaRepository.deleteBuildingId(building.getId());
            for(UserEntity userEntity : building.getStaffs()){
                userEntity.getBuildings().remove(building);
            }
            for(UserEntity userEntity : building.getUsers()){
                userEntity.getBuildingPriorities().remove(userEntity);
            }
            buildingRepository.delete(building);
        }
    }

    @Override
    public BuildingPageResponseDTO findAll(BuildingDTO model) {
        List<BuildingDTO> buildings = new ArrayList<>();
        if(model.getUrlMapping().equals(SystemConstant.ADMIN_BUILDING_ASSIGNMENT)){
            model.setStaffName(SecurityUtils.getPrincipal().getUsername());
            model.setStaffId(userRepository.findByUserName(model.getStaffName()).getId());
        }
        List<BuildingEntity> buildingEntities = buildingRepository.findAll(getBuildingBuilder(model)
                , new PageRequest(model.getCurrentPage(), model.getLimit()));
        for(BuildingEntity item : buildingEntities){
            BuildingDTO buildingDTO = buildingConverter.convertToDto(item);
            buildingDTO.setCreatedDateShowing(convertDateFormat(buildingDTO.getCreatedDate().toString().split(" ")[0]));
            buildings.add(buildingDTO);
        }
        BuildingPageResponseDTO result = new BuildingPageResponseDTO();
        result.setBuildings(buildings);
        result.setPage(model.getCurrentPage());
        result.setTotalPage((int) Math.ceil(buildingRepository.count() * 1.0 / model.getLimit()));
        return result;
    }
    private String convertDateFormat(String date){
        String[] arr = date.split("-");
        return arr[2] + "/"+ arr[1] +"/"+ arr[0];
    }

    private BuildingBuilder getBuildingBuilder(BuildingDTO model){
        return new BuildingBuilder.Builder()
                .setName(model.getName())
                .setAreaFrom(model.getAreaRentFrom())
                .setAreaTo(model.getAreaRentTo())
                .setNumberOfBasement(model.getNumberOfBasement())
                .setFloorArea(model.getFloorArea())
                .setCostFrom(model.getCostRentFrom())
                .setCostTo(model.getCostRentTo())
                .setDirection(model.getDirection())
                .setDistrict(model.getDistrict())
                .setLevel(model.getLevel())
                .setManagerName(model.getManagerName())
                .setManagerPhone(model.getManagerPhone())
                .setWard(model.getWard())
                .setStreet(model.getStreet())
                .setStaffName(model.getStaffName())
                .setCreatedBy(model.getCreatedBy())
                .setStaffId(model.getStaffId())
                .setTypes(model.getBuildingTypes())
                .build();
    }

    @Override
    public BuildingDTO findBuildingById(Long id) {
        if(buildingRepository.exists(id)){
            BuildingEntity buildingEntity = buildingRepository.findOne(id);
            BuildingDTO buildingDTO = buildingConverter.convertToDto(buildingEntity);

            if(buildingEntity.getRentAreas() != null){
                List<String> list =  buildingEntity.getRentAreas().stream().map(item -> {
                    if (item.getValue() != null){
                        return  item.getValue().toString();
                    }
                    return "";
                }).collect(Collectors.toList());
                buildingDTO.setRentArea(String.join(",", list));
            }


            if(buildingDTO.getType() != null && !buildingDTO.getType().equals(""))
                buildingDTO.setBuildingTypes(buildingEntity.getType().split(","));
            return buildingDTO;
        }
        return new BuildingDTO();
    }

    @Override
    public BuildingPageResponseDTO findAllBuildingPriorities(PageDTO page) {
        BuildingPageResponseDTO result = new BuildingPageResponseDTO();
        UserEntity userEntity = userRepository.findByUserName(SecurityUtils.getPrincipal().getUsername());
        int total = userEntity.getBuildingPriorities().size();
        int start = (page.getPage() - 1) * page.getMaxItems();
        int limit = start + page.getMaxItems();
        if(total < limit){
            limit = total;
        }
        for(int i = start; i < limit; i++){
            BuildingDTO buildingDTO = buildingConverter.convertToDto(userEntity.getBuildingPriorities().get(start));
            result.getBuildings().add(buildingDTO);
            start++;
        }
        result.setTotalPage((int)Math.ceil((total * 1.0) / page.getMaxItems()));
        result.setPage(page.getPage());
        return result;
    }

    // District && BuildingTypes
    @Override
    public Map<String, String> getAllDistricts() {
        Map<String, String> results = new LinkedHashMap<>();
        EnumSet.allOf(DistrictEnum.class)
                .forEach(item -> results.put(item.toString(), item.getDistrictFullName()));
        return results;
    }
    @Override
    public Map<String, String> getAllBuildingTypes() {
        Map<String,String> results = new LinkedHashMap<>();
        EnumSet.allOf(BuildingTypeEnum.class)
                .forEach(item -> results.put(item.toString(), item.getBuildingTypeName()));
        return results;
    }

    @Override
    public void addPriority(Long id) {
        UserEntity user = userRepository.findByUserName(SecurityUtils.getPrincipal().getUsername());
        BuildingEntity buildingEntity = buildingRepository.findOne(id);
        buildingEntity.getUsers().add(user);
        buildingRepository.save(buildingEntity);
    }

    @Override
    @Transactional
    public void deleteBuildingMyList(long[] ids) {
        UserEntity userEntity = userRepository.findByUserName(SecurityUtils.getPrincipal().getUsername());
        for(long id : ids){
            BuildingEntity buildingEntity = buildingRepository.findOne(id);
            userEntity.getBuildingPriorities().remove(buildingEntity);
            buildingEntity.getUsers().remove(userEntity);
        }
    }
}
