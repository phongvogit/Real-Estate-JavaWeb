package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.PageDTO;
import com.laptrinhjavaweb.dto.response.BuildingPageResponseDTO;

import java.util.Map;

public interface IBuildingService {
    //Save
    BuildingDTO saveNewBuilding(BuildingDTO buildingDTO);
    // Delete
    void deleteBuilding(Long[] ids);
    // !Find
    BuildingPageResponseDTO findAll(BuildingDTO model);
    BuildingDTO findBuildingById(Long id);
    BuildingPageResponseDTO findAllBuildingPriorities(PageDTO pageDTO);

    //Show District && BuildingType For Front
    Map<String, String> getAllDistricts();
    Map<String,String> getAllBuildingTypes();

    void addPriority(Long id);

    void deleteBuildingMyList(long[] ids);
}
