package com.laptrinhjavaweb.dto.response;

import com.laptrinhjavaweb.dto.UserDTO;

public class StaffResponseDTO {
    private String fullName;
    private Long staffId;
    private String check;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }

}
