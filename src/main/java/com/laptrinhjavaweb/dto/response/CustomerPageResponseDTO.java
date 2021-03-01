package com.laptrinhjavaweb.dto.response;

import com.laptrinhjavaweb.dto.CustomerDTO;

import java.util.ArrayList;
import java.util.List;

public class CustomerPageResponseDTO {
    private Integer page;
    private Integer totalPage;
    private List<CustomerDTO> customers = new ArrayList<>();

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

    public List<CustomerDTO> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomerDTO> customers) {
        this.customers = customers;
    }
}
