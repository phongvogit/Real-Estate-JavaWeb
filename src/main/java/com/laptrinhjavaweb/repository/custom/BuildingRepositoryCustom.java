package com.laptrinhjavaweb.repository.custom;

import com.laptrinhjavaweb.builder.BuildingBuilder;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.CustomerDTO;
import com.laptrinhjavaweb.dto.request.BuildingSearchRequestDto;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.page.PageRequest;

import java.util.List;

public interface BuildingRepositoryCustom {
    //Find
    List<BuildingEntity> findAll(BuildingBuilder model, PageRequest pageRequest);
    //Save
    void saveBuilding(BuildingEntity buildingEntity);
}
