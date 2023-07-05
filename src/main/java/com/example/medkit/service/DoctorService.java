package com.example.medkit.service;

import com.example.medkit.domain.DoctorEntity;
import com.example.medkit.dto.GeneralResponse;
import com.example.medkit.dto.request.DoctorDto;

import java.util.List;

public interface DoctorService {

    GeneralResponse<List<DoctorEntity>> getAllDoctors();
    GeneralResponse<List<DoctorEntity>> getDoctorByFilter(Integer code, String name, Integer expFrom, Integer expTo);

    GeneralResponse<DoctorEntity> getDoctorByPhone(String phoneNumber);
    GeneralResponse<List<DoctorEntity>> getDoctorByProfession(String prof);

    GeneralResponse<DoctorEntity> getDoctorById(Long id);

    GeneralResponse<DoctorEntity> saveDoctor(DoctorDto dto);

    GeneralResponse<DoctorEntity> getDoctorPatients(DoctorDto dto);
}
