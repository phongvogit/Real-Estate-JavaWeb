package com.laptrinhjavaweb.repository.custom.impl;

import com.laptrinhjavaweb.dto.request.BuildingSearchRequestDto;
import com.laptrinhjavaweb.entity.BuildingEntity;
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
    public List<BuildingEntity> findAll(BuildingSearchRequestDto model) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT b.* FROM building as b ");
        sql.append("WHERE TRUE ");
        sql = buildSqlBuildingSpecial(model, sql);
        sql = buildSqlBuildingCommon(model, sql);
        sql.append(" ORDER BY b.name ");
        sql.append(" LIMIT "+model.getStartPage() +","+model.getLimit()+" ");

        Query query = entityManager.createNativeQuery(sql.toString(), BuildingEntity.class);
        return query.getResultList();
    }

    private StringBuilder buildSqlBuildingCommon(BuildingSearchRequestDto model, StringBuilder sql) {
        // Staff
        if(model.getStaffId() != null){
            sql.append(" AND EXISTS(SELECT * FROM assignmentbuilding as asb " +
                    "WHERE asb.buildingid = b.id AND asb.staffid = "+model.getStaffId()+") ");
        }
        //Area
        if(model.getAreaRentFrom() != null || model.getAreaRentTo() != null){
            sql.append(" AND EXISTS ( SELECT * FROM rentArea as ra where ra.buildingid = b.id ");
            if(model.getAreaRentFrom() != null){
                sql.append(" AND ra.value >= " + model.getAreaRentFrom() + " ");
            }
            if(model.getAreaRentTo() != null){
                sql.append(" AND ra.value <= " + model.getAreaRentTo() + " ");
            }
            sql.append(")");
        }
        // RentCost
        if(model.getCostRentFrom() != null || model.getCostRentTo() != null){
            if(model.getCostRentFrom() != null){
                sql.append(" AND b.rentcost >= " + model.getCostRentFrom());
            }
            if(model.getCostRentTo() != null){
                sql.append(" AND b.rentcost <= " + model.getCostRentTo());
            }
        }
//        //Building Type
        if(model.getBuildingTypes() != null && model.getBuildingTypes().length != 0){
            sql.append(" AND ( ");
//            // Java 8
        if(model.getBuildingTypes() != null && model.getBuildingTypes().length != 0){
            String sqlTypes = Arrays.stream(model.getBuildingTypes())
                    .map(item -> "b.type like '%"+ item +"%'")
                    .collect(Collectors.joining(" OR "));
            sql.append(sqlTypes);
            sql.append(" )");
        }
        }
        return sql;
    }

    private StringBuilder buildSqlBuildingSpecial(BuildingSearchRequestDto model, StringBuilder sql) {
        try{
            Field[] fields = BuildingSearchRequestDto.class.getDeclaredFields();
            for(Field field : fields){
                field.setAccessible(true);
                if(field.get(model) != null && (!field.get(model).equals(""))){
                    if(!field.getName().equals("buildingTypes") && !field.getName().equals("staffId")
                            && !field.getName().startsWith("areaRent") && !field.getName().startsWith("costRent")
                            && !field.getName().startsWith("startPage") && !field.getName().startsWith("limit")
                            && !field.getName().startsWith("currentPage")){
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
