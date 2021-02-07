package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.dto.TransactionDTO;
import com.laptrinhjavaweb.entity.TransactionEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransactionConverter {

    @Autowired
    private ModelMapper modelMapper;

    public TransactionDTO convertToDto (TransactionEntity entity){
        TransactionDTO result = modelMapper.map(entity, TransactionDTO.class);
        return result;
    }

    public TransactionEntity convertToEntity (TransactionDTO dto){
        TransactionEntity result = modelMapper.map(dto, TransactionEntity.class);
        return result;
    }
}
