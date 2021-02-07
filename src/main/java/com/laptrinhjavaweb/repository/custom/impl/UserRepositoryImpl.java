package com.laptrinhjavaweb.repository.custom.impl;

import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.repository.custom.UserRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<UserEntity> findUserByBuildingId(Long buildingid) {
        if(buildingid == 0) return null;

        String sql = "select * from users as u\n" +
                "left join assignmentbuilding as asb on asb.staffid = u.id\n" +
                "where buildingid = "+ buildingid +" ;";
        Query query = entityManager.createNativeQuery(sql, UserEntity.class);
        return query.getResultList();
    }

}
