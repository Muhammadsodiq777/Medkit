package com.example.medkit.service;

import com.example.medkit.domain.PatientEntity;
import com.example.medkit.dto.GeneralResponse;
import com.example.medkit.dto.request.PatientDto;

import java.util.List;

public interface PatientService {

    GeneralResponse<List<PatientEntity>> getPatientUsers();

    GeneralResponse<PatientEntity> getPatientByPhone(String phoneNumber);

    GeneralResponse<PatientEntity> getPatientById(Long id);

    GeneralResponse<PatientEntity> savePatient(PatientDto patientDto);
}
