package com.example.medkit.service.impl;

import com.example.medkit.domain.PatientEntity;

import com.example.medkit.dto.GeneralResponse;

import com.example.medkit.dto.request.PatientDto;
import com.example.medkit.repository.PatientRepository;
import com.example.medkit.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository repository;

    @Override
    public GeneralResponse<List<PatientEntity>> getPatientUsers() {
        List<PatientEntity> all = repository.findAll();

        if (all.isEmpty())
            return new GeneralResponse<>(false, -1, "Foydalanuvchilar topilmadi", null);
        return new GeneralResponse<>(true, 1, "success", all);
    }


    @Override
    public GeneralResponse<PatientEntity> getPatientByPhone(String phoneNumber) {
        Optional<PatientEntity> byPhoneNumber = repository.findByPhoneNumberForPatient(phoneNumber);
        return byPhoneNumber.map(patientEntity -> new GeneralResponse<>(true, 1, "success", patientEntity)).orElseGet(() -> new GeneralResponse<>(false, -1, "Foydalanuvchilar topilmadi", null));
    }

    @Override
    public GeneralResponse<PatientEntity> getPatientById(Long id) {
        Optional<PatientEntity> byPhoneNumber = repository.findById(id);

        return byPhoneNumber.map(patientEntity -> new GeneralResponse<>(true, 1, "success", patientEntity)).orElseGet(() -> new GeneralResponse<>(false, -1, "Foydalanuvchilar topilmadi", null));
    }


    @Override
    public GeneralResponse<PatientEntity> savePatient(PatientDto patientDto) {

        Optional<PatientEntity> patient = repository.findByPhoneNumberForPatient(patientDto.getPhoneNumber());
        if (!patient.isPresent()){
            return new GeneralResponse<>(false, -1, "Bu raqam bilan foydalanuvchi mavjud", null);
        }
        else {
            PatientEntity patient1 = new PatientEntity();

            patient1.setFio(patientDto.getFio());
            patient1.setUserName(patientDto.getUserName());
            patient1.setPhoneNumber(patientDto.getPhoneNumber());
            patient1.setIsActive(1);
            patient1.setCreated_at(LocalDateTime.now());
            PatientEntity savedPatient = repository.save(patient1);
            return new GeneralResponse<>(true, 1, "success", savedPatient);
        }
    }

}
