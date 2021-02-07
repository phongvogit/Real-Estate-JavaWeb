package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.Enums.TransactionTypeEnum;
import com.laptrinhjavaweb.converter.BuildingConverter;
import com.laptrinhjavaweb.converter.TransactionConverter;
import com.laptrinhjavaweb.dto.TransactionDTO;
import com.laptrinhjavaweb.dto.response.TransResponseDTO;
import com.laptrinhjavaweb.entity.TransactionEntity;
import com.laptrinhjavaweb.repository.BuildingRepository;
import com.laptrinhjavaweb.repository.CustomerRepository;
import com.laptrinhjavaweb.repository.TransactionRepository;
import com.laptrinhjavaweb.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class TransactionService implements ITransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionConverter transactionConverter;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    @Transactional
    public void saveNewTransaction(TransactionDTO model) {
        TransactionEntity transactionEntity = transactionConverter.convertToEntity(model);
        if(customerRepository.exists(model.getCustomerId())){
            transactionEntity.setCustomer(customerRepository.findOne(model.getCustomerId()));
            transactionRepository.save(transactionEntity);
        }
    }

    @Override
    public TransResponseDTO getAllByCustomerId(Long id) {
        List<TransactionDTO> transactionDTOLists = new ArrayList<>();
        for(TransactionEntity item : transactionRepository.getTransactionsByCustomerId(id)){
            transactionDTOLists.add(transactionConverter.convertToDto(item));
        }
        TransResponseDTO transResponseDTO = new TransResponseDTO();
        transResponseDTO.setCustomerid(id);
        transResponseDTO.setTransDtoLists(transactionDTOLists);
        transResponseDTO.setTransactionTypeMaps(getTransactionMaps());
        return transResponseDTO;
    }

    private Map<String, String> getTransactionMaps() {
        Map<String, String> results = new LinkedHashMap<>();
        EnumSet.allOf(TransactionTypeEnum.class)
                .forEach(item -> results.put(item.toString(), item.getFullName()));
        return results;
    }
}
