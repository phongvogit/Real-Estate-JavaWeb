package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.dto.CustomerDTO;

import java.util.List;

public interface ICustomerService {
    //Save
    CustomerDTO saveNewCustomer(CustomerDTO customerDTO);
    //Delete
    void deleteCustomer(Long[] ids);
    // !Find
    List<CustomerDTO> findAll(CustomerDTO customerDTO);
    CustomerDTO findCustomerById(Long id);


}
