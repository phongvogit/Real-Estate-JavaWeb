package com.laptrinhjavaweb.dto;

import com.laptrinhjavaweb.entity.RentAreaEntity;
import com.laptrinhjavaweb.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;

public class BuildingDTO extends BaseDTO<BuildingDTO>{

    private String[] buildingTypes;
    private String name;
    private String ward;
    private String street;
    private String structure;
    private Integer numberOfBasement;
    private Integer floorArea;
    private String direction;
    private String level;
    private String rentAreaDescription;
    private String district;
    private Integer rentCost;
    private Integer costRentFrom;
    private Integer costRentTo;
    private String costDescription;
    private String serviceCost;
    private String carCost;
    private String motorCost;
    private String overtimeCost;
    private String type;
    private String electricBill;
    private String deposit;
    private String payment;
    private String timeRent;
    private String timeDecoration;
    private String managerName;
    private String managerPhone;
    private String rentArea;
    private Integer areaRentFrom;
    private Integer areaRentTo;
    private List<RentAreaEntity> rentAreas = new ArrayList<>();
    private String staffString;
    private List<UserEntity> staffs = new ArrayList<>();
    private Boolean checkUpdate = false;
    private String image;

    public Integer getCostRentFrom() {
        return costRentFrom;
    }

    public void setCostRentFrom(Integer costRentFrom) {
        this.costRentFrom = costRentFrom;
    }

    public Integer getCostRentTo() {
        return costRentTo;
    }

    public void setCostRentTo(Integer costRentTo) {
        this.costRentTo = costRentTo;
    }

    public String getRentArea() {
        return rentArea;
    }

    public void setRentArea(String rentArea) {
        this.rentArea = rentArea;
    }

    public Integer getAreaRentFrom() {
        return areaRentFrom;
    }

    public void setAreaRentFrom(Integer areaRentFrom) {
        this.areaRentFrom = areaRentFrom;
    }

    public Integer getAreaRentTo() {
        return areaRentTo;
    }

    public void setAreaRentTo(Integer areaRentTo) {
        this.areaRentTo = areaRentTo;
    }

    public String[] getBuildingTypes() {
        return buildingTypes;
    }

    public void setBuildingTypes(String[] buildingType) {
        this.buildingTypes = buildingType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStructure() {
        return structure;
    }

    public void setStructure(String structure) {
        this.structure = structure;
    }

    public Integer getNumberOfBasement() {
        return numberOfBasement;
    }

    public void setNumberOfBasement(Integer numberOfBasement) {
        this.numberOfBasement = numberOfBasement;
    }

    public Integer getFloorArea() {
        return floorArea;
    }

    public void setFloorArea(Integer floorArea) {
        this.floorArea = floorArea;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getRentAreaDescription() {
        return rentAreaDescription;
    }

    public void setRentAreaDescription(String rentAreaDescription) {
        this.rentAreaDescription = rentAreaDescription;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Integer getRentCost() {
        return rentCost;
    }

    public void setRentCost(Integer rentCost) {
        this.rentCost = rentCost;
    }

    public String getCostDescription() {
        return costDescription;
    }

    public void setCostDescription(String costDescription) {
        this.costDescription = costDescription;
    }

    public String getServiceCost() {
        return serviceCost;
    }

    public void setServiceCost(String serviceCost) {
        this.serviceCost = serviceCost;
    }

    public String getCarCost() {
        return carCost;
    }

    public void setCarCost(String carCost) {
        this.carCost = carCost;
    }

    public String getMotorCost() {
        return motorCost;
    }

    public void setMotorCost(String motorCost) {
        this.motorCost = motorCost;
    }

    public String getOvertimeCost() {
        return overtimeCost;
    }

    public void setOvertimeCost(String overtimeCost) {
        this.overtimeCost = overtimeCost;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getElectricBill() {
        return electricBill;
    }

    public void setElectricBill(String electricBill) {
        this.electricBill = electricBill;
    }

    public String getDeposit() {
        return deposit;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getTimeRent() {
        return timeRent;
    }

    public void setTimeRent(String timeRent) {
        this.timeRent = timeRent;
    }

    public String getTimeDecoration() {
        return timeDecoration;
    }

    public void setTimeDecoration(String timeDecoration) {
        this.timeDecoration = timeDecoration;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getManagerPhone() {
        return managerPhone;
    }

    public void setManagerPhone(String managerPhone) {
        this.managerPhone = managerPhone;
    }

    public List<RentAreaEntity> getRentAreas() {
        return rentAreas;
    }

    public void setRentAreas(List<RentAreaEntity> rentAreas) {
        this.rentAreas = rentAreas;
    }

    public Boolean getCheckUpdate() {
        return checkUpdate;
    }

    public void setCheckUpdate(Boolean checkUpdate) {
        this.checkUpdate = checkUpdate;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public List<UserEntity> getStaffs() {
        return staffs;
    }

    public void setStaffs(List<UserEntity> staffs) {
        this.staffs = staffs;
    }

    public String getStaffString() {
        return staffString;
    }

    public void setStaffString(String staffString) {
        this.staffString = staffString;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
