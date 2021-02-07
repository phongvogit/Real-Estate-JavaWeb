package com.laptrinhjavaweb.dto.response;

import com.laptrinhjavaweb.dto.TransactionDTO;

import java.util.List;
import java.util.Map;

public class TransResponseDTO {
    private Long customerid;
    private List<TransactionDTO> transDtoLists;
    private Map<String, String> transactionTypeMaps;

    public List<TransactionDTO> getTransDtoLists() {
        return transDtoLists;
    }

    public void setTransDtoLists(List<TransactionDTO> transDtoLists) {
        this.transDtoLists = transDtoLists;
    }

    public Map<String, String> getTransactionTypeMaps() {
        return transactionTypeMaps;
    }

    public void setTransactionTypeMaps(Map<String, String> transactionTypeMaps) {
        this.transactionTypeMaps = transactionTypeMaps;
    }

    public Long getCustomerid() {
        return customerid;
    }

    public void setCustomerid(Long customerid) {
        this.customerid = customerid;
    }
}
