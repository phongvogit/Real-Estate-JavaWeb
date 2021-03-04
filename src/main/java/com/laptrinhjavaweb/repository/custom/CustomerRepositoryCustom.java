package com.laptrinhjavaweb.repository.custom;

import com.laptrinhjavaweb.dto.CustomerDTO;
import com.laptrinhjavaweb.entity.CustomerEntity;
import com.laptrinhjavaweb.page.PageRequest;

import java.util.List;

public interface CustomerRepositoryCustom {
    List<CustomerEntity> findAll(CustomerDTO model);
}
