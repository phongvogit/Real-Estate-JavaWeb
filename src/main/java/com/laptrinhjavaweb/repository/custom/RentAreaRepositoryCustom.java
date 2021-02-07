package com.laptrinhjavaweb.repository.custom;

import com.laptrinhjavaweb.entity.RentAreaEntity;

public interface RentAreaRepositoryCustom {
    void saveRentArea(RentAreaEntity rentAreaEntity);
    void deleteBuildingId(Long buildingId);
}
