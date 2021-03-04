package com.laptrinhjavaweb.dto;

import java.util.ArrayList;
import java.util.List;

public class UserDTO extends BaseDTO {

    private String userName;
    private String fullName;
    private String password;
    private String phone;
    private String email;
    private String address;
    private Integer status;
    private String role;
    private List<String> codeRoles = new ArrayList<>();
    private List<RoleDTO> roles = new ArrayList<>();

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<RoleDTO> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleDTO> roles) {
        this.roles = roles;
    }

    public List<String> getCodeRoles() {
        return codeRoles;
    }

    public void setCodeRoles(List<String> codeRoles) {
        this.codeRoles = codeRoles;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
