package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.Enums.BuildingTypeEnum;
import com.laptrinhjavaweb.Enums.DistrictEnum;
import com.laptrinhjavaweb.converter.BuildingConverter;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.request.BuildingSearchRequestDto;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.RentAreaEntity;
import com.laptrinhjavaweb.repository.RentAreaRepository;
import com.laptrinhjavaweb.repository.BuildingRepository;
import com.laptrinhjavaweb.repository.UserRepository;
import com.laptrinhjavaweb.service.IBuildingService;
import org.apache.commons.lang.StringUtils;
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
        //BuildingTypes
        if(model.getBuildingTypes() != null && model.getBuildingTypes().length != 0)
            model.setType(String.join(",", model.getBuildingTypes()));
        BuildingEntity buildingEntity = buildingConverter.convertToEntity(model);;
        // Saved Staffs
        if(model.getStaffIds() != null && model.getStaffIds().length != 0){
            buildingEntity = setStaffLists(buildingEntity, model);
        }
        //buildingEntity = saveRentArea(buildingEntity, model);
        //Saved Building
        buildingRepository.save(buildingEntity);
        //Saved RentArea
        saveRentArea(buildingEntity, model);
        return buildingConverter.convertToDto(buildingEntity);
    }

    private BuildingEntity setStaffLists(BuildingEntity buildingEntity, BuildingDTO model){
        if(buildingRepository.exists(model.getId())){
            buildingEntity = buildingRepository.findOne(model.getId());
            buildingEntity.getStaffs().clear();
            for(Long id : model.getStaffIds()){
                buildingEntity.getStaffs().add(userRepository.findOne(id));
            }
        }
        return buildingEntity;
    }


    private void saveRentArea(BuildingEntity buildingEntity, BuildingDTO model){
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
////                    buildingEntity.getRentAreas().add(rentAreaEntity);
                    rentAreaRepository.save(rentAreaEntity);
                }catch (NumberFormatException e){
                    System.out.println(i + "is not valid number");
                }
            }
        }
    }

    @Override
    public void deleteBuilding(Long[] ids) {
        for(Long i : ids){
            buildingRepository.delete(i);
        }
    }

    @Override
    public List<BuildingDTO> findAll(BuildingSearchRequestDto model) {
        List<BuildingDTO> results = new ArrayList<>();
        for(BuildingEntity item : buildingRepository.findAll(model)){
            BuildingDTO buildingDTO = buildingConverter.convertToDto(item);
            results.add(buildingDTO);
        }
        return results;
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
        return null;
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
}
