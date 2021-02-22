package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.Enums.BuildingTypeEnum;
import com.laptrinhjavaweb.Enums.DistrictEnum;
import com.laptrinhjavaweb.converter.BuildingConverter;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.request.BuildingSearchRequestDto;
import com.laptrinhjavaweb.dto.response.BuildingPageResponseDTO;
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


    @Override
    @Transactional
    public BuildingDTO saveNewBuilding(BuildingDTO model ) {
        //BuildingTypes
        if(model.getBuildingTypes() != null && model.getBuildingTypes().length != 0)
            model.setType(String.join(",", model.getBuildingTypes()));
        BuildingEntity buildingEntity = buildingConverter.convertToEntity(model);
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
    public void deleteBuilding(Long[] ids) {
        for(Long i : ids){
            buildingRepository.delete(i);
        }
    }

    @Override
    public BuildingPageResponseDTO findAll(BuildingSearchRequestDto model) {
        List<BuildingDTO> buildings = new ArrayList<>();
        model.setStartPage((model.getCurrentPage() - 1) * model.getLimit());
        for(BuildingEntity item : buildingRepository.findAll(model)){
            BuildingDTO buildingDTO = buildingConverter.convertToDto(item);
            buildings.add(buildingDTO);
        }
        BuildingPageResponseDTO result = new BuildingPageResponseDTO();
        result.setBuildings(buildings);
        result.setPage(model.getCurrentPage());
        result.setTotalPage((int) Math.ceil(buildingRepository.count() * 1.0 / model.getLimit()));
        return result;
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
