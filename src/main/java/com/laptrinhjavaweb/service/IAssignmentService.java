package com.laptrinhjavaweb.service;

public interface IAssignmentService {
    void saveAssignmentForBuilding(Long buildingId, Long[] staffIds);
}
