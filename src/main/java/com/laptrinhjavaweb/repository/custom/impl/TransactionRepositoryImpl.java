package com.laptrinhjavaweb.repository.custom.impl;

import com.laptrinhjavaweb.entity.TransactionEntity;
import com.laptrinhjavaweb.repository.custom.TransactionRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class TransactionRepositoryImpl implements TransactionRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<TransactionEntity> getTransactionsByCustomerId(Long id) {
        String sql = "SELECT * FROM transaction as t where t.customerid = "+ id +" ";
        Query query = entityManager.createNativeQuery(sql, TransactionEntity.class);
        return query.getResultList();
    }
}
