package com.example.medkit.repository.custom;

import com.example.medkit.domain.DoctorEntity;
import com.example.medkit.domain.Recipes;

import java.util.List;

public interface CustomRepository {

    List<DoctorEntity> getDoctorByFilter(String name, Integer expFrom, Integer expTo);

    List<DoctorEntity> getAllDoctors();
    List<DoctorEntity> getDoctorByProfession(String prof);

    List<Recipes> getRecipesByDoctorAndUserId(Long doctorId, Long patientId);



}
