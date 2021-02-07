package com.laptrinhjavaweb.repository.custom.impl;

import com.laptrinhjavaweb.entity.RentAreaEntity;
import com.laptrinhjavaweb.repository.custom.RentAreaRepositoryCustom;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
public class RentAreaRepositoryImpl implements RentAreaRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void saveRentArea(RentAreaEntity rentAreaEntity) {
        entityManager.persist(rentAreaEntity);
    }

    @Override
    @Transactional
    public void deleteBuildingId(Long buildingId) {
        Query query = entityManager.createNativeQuery("DELETE FROM rentarea WHERE buildingid = ?");
        query.setParameter(1, buildingId);
        query.executeUpdate();
    }
}
