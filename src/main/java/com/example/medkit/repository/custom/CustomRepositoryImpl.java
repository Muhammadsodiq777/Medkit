package com.example.medkit.repository.custom;


import com.example.medkit.domain.DoctorEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomRepositoryImpl implements CustomRepository {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<DoctorEntity> getDoctorByFilter(String name, Integer expFrom, Integer expTo) {
        String queryStr = "select * from md_doctor where profession " +
                "like '%"+name+"%' and experience between :expFrom and :expTo";

        Query query = entityManager.createNativeQuery(queryStr, DoctorEntity.class);
        query.setParameter("expFrom", expTo);

        List<DoctorEntity> list = query.getResultList();
        return list;
    }
}
