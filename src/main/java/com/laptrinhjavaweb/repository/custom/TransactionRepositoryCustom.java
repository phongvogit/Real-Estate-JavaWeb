package com.laptrinhjavaweb.repository.custom;

import com.laptrinhjavaweb.dto.CustomerDTO;
import com.laptrinhjavaweb.entity.TransactionEntity;

import java.util.List;

public interface TransactionRepositoryCustom {
    List<TransactionEntity> getTransactionsByCustomerId(Long id);
}
