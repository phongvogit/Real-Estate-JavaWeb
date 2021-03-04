package com.laptrinhjavaweb.repository.custom.impl;

import com.laptrinhjavaweb.builder.BuildingBuilder;
import com.laptrinhjavaweb.dto.request.BuildingSearchRequestDto;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.page.PageRequest;
import com.laptrinhjavaweb.repository.custom.BuildingRepositoryCustom;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BuildingRepositoryImpl implements BuildingRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<BuildingEntity> findAll(BuildingBuilder model, PageRequest pageRequest) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT b.* FROM building as b ");
        sql.append("WHERE TRUE ");
        sql = buildSqlBuildingSpecial(model, sql);
        sql = buildSqlBuildingCommon(model, sql);
        sql.append(" ORDER BY b.name ");
        sql.append(" LIMIT "+pageRequest.getOffset() +","+pageRequest.getLimit()+" ");

        Query query = entityManager.createNativeQuery(sql.toString(), BuildingEntity.class);
        return query.getResultList();
    }

    private StringBuilder buildSqlBuildingCommon(BuildingBuilder model, StringBuilder sql) {
        // Staff
        if(model.getStaffId() != null){
            sql.append(" AND EXISTS(SELECT * FROM assignmentbuilding as asb " +
                    "WHERE asb.buildingid = b.id AND asb.staffid = "+model.getStaffId()+") ");
        }
        //Area
        if(model.getAreaFrom() != null || model.getAreaTo() != null){
            sql.append(" AND EXISTS ( SELECT * FROM rentArea as ra where ra.buildingid = b.id ");
            if(model.getAreaFrom() != null){
                sql.append(" AND ra.value >= " + model.getAreaFrom() + " ");
            }
            if(model.getAreaTo() != null){
                sql.append(" AND ra.value <= " + model.getAreaTo() + " ");
            }
            sql.append(")");
        }
        // RentCost
        if(model.getCostFrom() != null || model.getCostTo() != null){
            if(model.getCostFrom() != null){
                sql.append(" AND b.rentcost >= " + model.getCostFrom());
            }
            if(model.getCostTo() != null){
                sql.append(" AND b.rentcost <= " + model.getCostTo());
            }
        }
//        //Building Type
        if(model.getTypes() != null && model.getTypes().length != 0){
            sql.append(" AND ( ");
            String sqlTypes = Arrays.stream(model.getTypes())
                    .map(item -> "b.type like '%"+ item +"%'")
                    .collect(Collectors.joining(" OR "));
            sql.append(sqlTypes);
            sql.append(" )");
        }
        return sql;
    }

    private StringBuilder buildSqlBuildingSpecial(BuildingBuilder model, StringBuilder sql) {
        try{
            Field[] fields = BuildingBuilder.class.getDeclaredFields();
            for(Field field : fields){
                field.setAccessible(true);
                if(field.get(model) != null && (!field.get(model).equals(""))){
                    if(!field.getName().equals("types") && !field.getName().equals("staffId")
                            && !field.getName().startsWith("area") && !field.getName().startsWith("cost")
                            && !field.getName().startsWith("staffName") && !field.getName().startsWith("createdBy")){
                        if(field.getType().getName().equals("java.lang.String")){
                            sql.append("AND b."+field.getName().toLowerCase()+" LIKE '%"+field.get(model)+"%' ");
                        } else if(field.getType().getName().equals("java.lang.Integer")){
                            sql.append("AND b."+field.getName().toLowerCase()+" = "+field.get(model)+" ");
                        }
                    }
                }
            }
            return sql;
        }catch (Exception ex){
            return null;
        }
    }


    @Override
    @Transactional
    public void saveBuilding(BuildingEntity buildingEntity) {
        entityManager.persist(buildingEntity);
    }
}
