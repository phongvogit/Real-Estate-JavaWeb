package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.dto.TransactionDTO;
import com.laptrinhjavaweb.dto.response.TransResponseDTO;

public interface ITransactionService {
    TransResponseDTO getAllByCustomerId(Long id);
    void saveNewTransaction(TransactionDTO model);
}
