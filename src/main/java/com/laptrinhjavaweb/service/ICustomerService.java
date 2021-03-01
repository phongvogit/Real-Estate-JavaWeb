package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.dto.CustomerDTO;
import com.laptrinhjavaweb.dto.response.CustomerPageResponseDTO;

import java.util.List;

public interface ICustomerService {
    //Save
    CustomerDTO saveNewCustomer(CustomerDTO customerDTO);
    //Delete
    void deleteCustomer(Long[] ids);
    // !Find
    CustomerPageResponseDTO findAll(CustomerDTO customerDTO);
    CustomerDTO findCustomerById(Long id);


}
