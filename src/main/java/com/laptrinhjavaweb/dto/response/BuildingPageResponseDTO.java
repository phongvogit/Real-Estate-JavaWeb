package com.laptrinhjavaweb.dto.response;

import com.laptrinhjavaweb.dto.BuildingDTO;

import java.util.ArrayList;
import java.util.List;

public class BuildingPageResponseDTO {
    private Integer page;
    private Integer totalPage;
    private List<BuildingDTO> buildings = new ArrayList<>();

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public List<BuildingDTO> getBuildings() {
        return buildings;
    }

    public void setBuildings(List<BuildingDTO> buildings) {
        this.buildings = buildings;
    }
}
