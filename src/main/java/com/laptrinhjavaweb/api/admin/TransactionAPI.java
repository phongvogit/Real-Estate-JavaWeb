package com.laptrinhjavaweb.api.admin;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.TransactionDTO;
import com.laptrinhjavaweb.dto.response.ResponseDTO;
import com.laptrinhjavaweb.service.impl.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "trnsactionAPIOfAdmin")
@RequestMapping(value = "/api/transaction")
public class TransactionAPI {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseDTO createBuilding(@RequestBody TransactionDTO model){
        ResponseDTO responseDTO = new ResponseDTO();
        transactionService.saveNewTransaction(model);
        responseDTO.setMessage("success");
        return responseDTO;
    }
}
