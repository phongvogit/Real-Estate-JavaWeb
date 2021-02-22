package com.laptrinhjavaweb.dto.response;

import com.laptrinhjavaweb.dto.UserDTO;

public class StaffResponseDTO {
    private String fullName;
    private Long id;
    private String check;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }


    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
