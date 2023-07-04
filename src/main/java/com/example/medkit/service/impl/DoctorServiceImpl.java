package com.example.medkit.service.impl;

import com.example.medkit.domain.DoctorEntity;
import com.example.medkit.dto.GeneralResponse;
import com.example.medkit.dto.request.DoctorDto;
import com.example.medkit.repository.DoctorRepository;
import com.example.medkit.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository repository;

    @Override
    public GeneralResponse<List<DoctorEntity>> getAllDoctors() {
        List<DoctorEntity> all = repository.findAll();

        if (all.isEmpty())
            return new GeneralResponse<>(false, -1, "Foydalanuvchilar topilmadi", null);
        return new GeneralResponse<>(true, 1, "success", all);
    }


    @Override
    public GeneralResponse<DoctorEntity> getDoctorByPhone(String phoneNumber) {
        Optional<DoctorEntity> byPhoneNumber = repository.findByPhoneNumber(phoneNumber);
        return byPhoneNumber.map(doctorEntity -> new GeneralResponse<>(true, 1, "success", doctorEntity)).orElseGet(() -> new GeneralResponse<>(false, -1, "Foydalanuvchilar topilmadi", null));
    }

    @Override
    public GeneralResponse<DoctorEntity> getDoctorById(Long id) {
        Optional<DoctorEntity> byPhoneNumber = repository.findById(id);

        return byPhoneNumber.map(doctorEntity -> new GeneralResponse<>(true, 1, "success", doctorEntity)).orElseGet(() -> new GeneralResponse<>(false, -1, "Foydalanuvchilar topilmadi", null));
    }

    @Override
    public GeneralResponse<DoctorEntity> saveDoctor(DoctorDto dto) {
        Optional<DoctorEntity> optionalDoctor = repository.findByPhoneNumber(dto.getPhoneNumber());
        if (optionalDoctor.isEmpty())
            return new GeneralResponse<>(false, -1, "Bu raqam bilan doctor mavjud", null);

        DoctorEntity doctor = new DoctorEntity();

        doctor.setFio(dto.getFio());
        doctor.setPhoneNumber(dto.getPhoneNumber());
        doctor.setIsActive(1);
        doctor.setCreated_at(LocalDateTime.now());

        DoctorEntity savedDoctor = repository.save(doctor);
        return new GeneralResponse<>(true, 1, "success", savedDoctor);
    }

}
