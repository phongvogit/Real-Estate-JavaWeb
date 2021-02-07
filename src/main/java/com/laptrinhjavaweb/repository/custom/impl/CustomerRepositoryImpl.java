package com.laptrinhjavaweb.repository.custom.impl;

import com.laptrinhjavaweb.dto.CustomerDTO;
import com.laptrinhjavaweb.entity.CustomerEntity;
import com.laptrinhjavaweb.repository.custom.CustomerRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.util.List;

@Repository
public class CustomerRepositoryImpl implements CustomerRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<CustomerEntity> findAll(CustomerDTO model) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM customers AS c WHERE TRUE ");
        sql = buildSqlCommon(sql, model);

        Query query = entityManager.createNativeQuery(sql.toString(), CustomerEntity.class);
        return query.getResultList();
    }

    private StringBuilder buildSqlCommon(StringBuilder sql, CustomerDTO model){
        try{
            for(Field field :  CustomerDTO.class.getDeclaredFields()){
                field.setAccessible(true);
                if(field.get(model) != null && !field.get(model).equals("")){
                    if(field.getType().equals("java.lang.String")){
                        sql.append(" AND c."+field.getName().toLowerCase()+" LIKE '%"+field.get(model)+"%' ");
                    }else if(field.getType().equals("java.lang.Integer")){
                        sql.append(" AND c."+field.getName().toLowerCase()+" = "+field.get(model)+" ");
                    }
                }
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return sql;
    }
}
