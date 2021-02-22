package com.laptrinhjavaweb.dto.request;


public class AssignmentRequestDto {
    private Long buildingId;
    private Long[] staffIds;

    public Long getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }

    public Long[] getStaffIds() {
        return staffIds;
    }

    public void setStaffIds(Long[] staffIds) {
        this.staffIds = staffIds;
    }
}
