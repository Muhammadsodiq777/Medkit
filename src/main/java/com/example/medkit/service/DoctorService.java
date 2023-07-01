package com.example.medkit.service;

import com.example.medkit.domain.DoctorEntity;
import com.example.medkit.dto.GeneralResponse;
import com.example.medkit.dto.request.DoctorDto;

import java.util.List;

public interface DoctorService {

    GeneralResponse<List<DoctorEntity>> getAllDoctors();

    GeneralResponse<DoctorEntity> getDoctorByPhone(String phoneNumber);

    GeneralResponse<DoctorEntity> getDoctorById(Long id);

    GeneralResponse<DoctorEntity> saveDoctor(DoctorDto dto);
}
