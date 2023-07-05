package com.example.medkit.repository.custom;


import com.example.medkit.domain.DoctorEntity;
import com.example.medkit.domain.Recipes;
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
        query.setParameter("expFrom", expFrom);
        query.setParameter("expTo", expTo);

        List<DoctorEntity> list = query.getResultList();
        return list;
    }

    @Override
    public List<DoctorEntity> getAllDoctors() {
       String query= "select * from md_doctor order by rating desc";
        Query nativeQuery = entityManager.createNativeQuery(query, DoctorEntity.class);
        List<DoctorEntity> resultList = nativeQuery.getResultList();
        return  resultList;
    }

    @Override
    public List<DoctorEntity> getDoctorByProfession(String prof) {

        String queryStr = "select * from md_doctor md where md.profession like '%"+prof+"%' ";
        Query query = entityManager.createNativeQuery(queryStr, DoctorEntity.class);

        List<DoctorEntity> list = query.getResultList();
        return list;
    }

    @Override
    public List<Recipes> getRecipesByDoctorAndUserId(Long doctorId, Long patientId) {

        String  query= "select * from md_recipes md where md.doctor_id = :doctorId and md.patient_id = :patientId";

        Query nativeQuery = entityManager.createNativeQuery(query, Recipes.class);
        nativeQuery.setParameter("doctorId", doctorId);
        nativeQuery.setParameter("patientId", patientId);

        List<Recipes> list = nativeQuery.getResultList();
        return list;
    }
}
