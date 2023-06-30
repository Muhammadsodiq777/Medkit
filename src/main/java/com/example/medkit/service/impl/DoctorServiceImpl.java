package com.example.medkit.service.impl;

import com.example.medkit.domain.DoctorEntity;
import com.example.medkit.domain.UserEntity;
import com.example.medkit.dto.GeneralResponse;
import com.example.medkit.dto.request.DoctorDto;
import com.example.medkit.repository.DoctorRepository;
import com.example.medkit.repository.UserRepository;
import com.example.medkit.service.DoctorService;
import com.example.medkit.service.UserService;
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
    public GeneralResponse<List<DoctorEntity>> getDoctors() {
        return null;
    }

    @Override
    public GeneralResponse<DoctorEntity> getDoctorByPhone(String phoneNumber) {
        Optional<DoctorEntity> byPhoneNumber = repository.findByPhoneNumber(phoneNumber);
        if (!byPhoneNumber.isPresent())
            return new GeneralResponse<>(false, -1, "Foydalanuvchilar topilmadi", null);
        return new GeneralResponse<>(true, 1, "success", byPhoneNumber.get());
    }

    @Override
    public GeneralResponse<DoctorEntity> getDoctorById(Long id) {
        Optional<DoctorEntity> byPhoneNumber = repository.findById(id);

        if (!byPhoneNumber.isPresent())
            return new GeneralResponse<>(false, -1, "Foydalanuvchilar topilmadi", null);
        return new GeneralResponse<>(true, 1, "success", byPhoneNumber.get());
    }

    @Override
    public GeneralResponse<DoctorEntity> saveUser(DoctorDto dto) {
        return null;
    }

}
