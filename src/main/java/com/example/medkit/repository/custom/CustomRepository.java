package com.example.medkit.repository.custom;

import com.example.medkit.domain.DoctorEntity;

import java.util.List;

public interface CustomRepository {

    List<DoctorEntity> getDoctorByFilter(String name, Integer expFrom, Integer expTo);

}
