package com.example.medkit.service.impl;

import com.example.medkit.domain.PatientEntity;

import com.example.medkit.dto.GeneralResponse;

import com.example.medkit.repository.PatientRepository;
import com.example.medkit.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
        Optional<PatientEntity> byPhoneNumber = repository.findByPhoneNumber(phoneNumber);
        if (!byPhoneNumber.isPresent())
            return new GeneralResponse<>(false, -1, "Foydalanuvchilar topilmadi", null);
        return new GeneralResponse<>(true, 1, "success", byPhoneNumber.get());
    }

    @Override
    public GeneralResponse<PatientEntity> getPatientById(Long id) {
        Optional<PatientEntity> byPhoneNumber = repository.findById(id);

        if (!byPhoneNumber.isPresent())
            return new GeneralResponse<>(false, -1, "Foydalanuvchilar topilmadi", null);
        return new GeneralResponse<>(true, 1, "success", byPhoneNumber.get());
    }

}
