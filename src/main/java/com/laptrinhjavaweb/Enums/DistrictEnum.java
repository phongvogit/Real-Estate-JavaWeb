package com.laptrinhjavaweb.Enums;


import java.util.stream.Stream;

public enum DistrictEnum {

    DISTRICT_1("District 1"),
    DISTRICT_2("District 2"),
    DISTRICT_3("District 3"),
    BINH_TAN("Binh Tan District"),
    BINH_THANH("Binh Thanh District"),
    THU_DUC("Thu Duc District");

    private String districtFullName;

    DistrictEnum(String districtFullName) {
        this.districtFullName = districtFullName;
    }

    // standard getters and setters 

    public static Stream<DistrictEnum> stream() {
        return Stream.of(DistrictEnum.values());
    }

    public String getDistrictFullName() {
        return districtFullName;
    }

}


