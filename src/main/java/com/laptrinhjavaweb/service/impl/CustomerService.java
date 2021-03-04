package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.converter.CustomerConverter;
import com.laptrinhjavaweb.dto.CustomerDTO;
import com.laptrinhjavaweb.dto.response.CustomerPageResponseDTO;
import com.laptrinhjavaweb.entity.CustomerEntity;
import com.laptrinhjavaweb.entity.TransactionEntity;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.repository.CustomerRepository;
import com.laptrinhjavaweb.repository.TransactionRepository;
import com.laptrinhjavaweb.repository.UserRepository;
import com.laptrinhjavaweb.security.utils.SecurityUtils;
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
    TransactionRepository transactionRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public CustomerDTO saveNewCustomer(CustomerDTO model) {
        CustomerEntity customerEntity = customerConverter.convertToEntity(model);
        if(model.getStaffIds() != null){
            customerEntity = setStaffList(customerEntity, model);
        }
        if(model.getId() != null){
            CustomerEntity oldCustomer = custumerRepository.findOne(model.getId());
            customerEntity.setCreatedBy(oldCustomer.getCreatedBy());
            customerEntity.setCreatedDate(oldCustomer.getCreatedDate());
            customerEntity.setUsers(oldCustomer.getUsers());
            customerEntity.setTransactions(oldCustomer.getTransactions());
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
    @Transactional
    public void deleteCustomer(Long[] ids) {
        for(Long i : ids){
            CustomerEntity customer = custumerRepository.findOne(i);
            transactionRepository.deleteTransactionByCustomerId(i);
            for(UserEntity userEntity : customer.getUsers()){
                userEntity.getCustomers().remove(customer);
            }
            custumerRepository.delete(customer);
        }
    }

    @Override
    public CustomerPageResponseDTO findAll(CustomerDTO model) {
        if(SecurityUtils.getAuthorities().stream().anyMatch(item -> item.equals("ROLE_STAFF"))){
            model.setStaffId(userRepository.findByUserName(SecurityUtils.getPrincipal().getUsername()).getId());
        }
        CustomerPageResponseDTO result = new CustomerPageResponseDTO();
        model.setStartPage((model.getCurrentPage() - 1) * model.getLimit());
        for(CustomerEntity item : custumerRepository.findAll(model)){
            CustomerDTO customerDTO = customerConverter.convertToDto(item);
            customerDTO.setCreatedDateShowing(convertDateFormat(item.getCreatedDate().toString().split(" ")[0]));
            result.getCustomers().add(customerDTO);
        }
        result.setPage(model.getCurrentPage());
        result.setTotalPage((int) Math.ceil(custumerRepository.count() * 1.0 / model.getLimit()));
       return result;
    }

    private String convertDateFormat(String date){
        String[] arr = date.split("-");
        return arr[2] + "/"+ arr[1] +"/"+ arr[0];
    }

    @Override
    public CustomerDTO findCustomerById(Long id) {
        return customerConverter.convertToDto(custumerRepository.getOne(id));
    }

}
