package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.converter.CustomerConverter;
import com.laptrinhjavaweb.dto.CustomerDTO;
import com.laptrinhjavaweb.entity.CustomerEntity;
import com.laptrinhjavaweb.repository.CustomerRepository;
import com.laptrinhjavaweb.repository.UserRepository;
import com.laptrinhjavaweb.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    CustomerConverter customerConverter;

    @Autowired
    CustomerRepository custumerRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public CustomerDTO saveNewCustomer(CustomerDTO model) {
        CustomerEntity customerEntity = customerConverter.convertToEntity(model);
        if(model.getStaffIds() != null){
            customerEntity = setStaffList(customerEntity, model);
        }
        return customerConverter.convertToDto(custumerRepository.save(customerEntity));
    }

    CustomerEntity setStaffList(CustomerEntity customerEntity, CustomerDTO model){
        if(custumerRepository.exists(customerEntity.getId())){
            customerEntity = custumerRepository.findOne(customerEntity.getId());
            customerEntity.getUsers().clear();
            for(Long id : model.getStaffIds()){
                customerEntity.getUsers().add(userRepository.findOne(id));
            }
        }
        return customerEntity;
    }

    @Override
    public void deleteCustomer(Long[] ids) {
        for(Long i : ids){
            custumerRepository.delete(i);
        }
    }

    @Override
    public List<CustomerDTO> findAll(CustomerDTO customerDTO) {
        List<CustomerDTO> results = new ArrayList<>();
        for(CustomerEntity item : custumerRepository.findAll(customerDTO)){
            results.add(customerConverter.convertToDto(item));
        }
       return results;
    }

    @Override
    public CustomerDTO findCustomerById(Long id) {
        return customerConverter.convertToDto(custumerRepository.getOne(id));
    }

}
