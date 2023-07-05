package com.example.medkit.repository.custom;

import com.example.medkit.domain.DoctorEntity;
import com.example.medkit.domain.PatientEntity;
import com.example.medkit.domain.Recipes;

import java.util.List;
import java.util.Optional;

public interface CustomRepository {


    List<DoctorEntity> getDoctorByFilter(String name, Integer expFrom, Integer expTo);

    List<DoctorEntity> getAllDoctors();
    List<DoctorEntity> getDoctorByProfession(String prof);

    List<Recipes> getRecipesByDoctorAndUserId(Long doctorId, Long patientId);

    Optional<DoctorEntity> findByPhoneNumberForDoctor(String phoneNumber);

    Optional<PatientEntity> findByPhoneNumberForPatient(String phoneNumber);

}
