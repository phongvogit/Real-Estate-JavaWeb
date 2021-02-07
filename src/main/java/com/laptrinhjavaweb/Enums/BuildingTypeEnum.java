package com.laptrinhjavaweb.Enums;

import java.util.stream.Stream;

public enum BuildingTypeEnum {
    //public static final String buildingTypeName = "Ground Floor,Detached House,Furnished House";

    GROUND_FLOOR("Ground Floor"),
    DETACHED_HOUSE("Detached House"),
    FURNISHED_HOUSE("Furnished House");

    private String buildingTypeName;

    BuildingTypeEnum(String districtFullName) {
        this.buildingTypeName = districtFullName;
    }

    // standard getters and setters

    public static Stream<DistrictEnum> stream() {
        return Stream.of(DistrictEnum.values());
    }

    public String getBuildingTypeName() {
        return buildingTypeName;
    }

}
