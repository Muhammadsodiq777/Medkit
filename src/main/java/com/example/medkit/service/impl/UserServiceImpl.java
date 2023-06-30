package com.example.medkit.service.impl;

import com.example.medkit.domain.UserEntity;
import com.example.medkit.dto.GeneralResponse;
import com.example.medkit.dto.request.UserDto;
import com.example.medkit.repository.UserRepository;
import com.example.medkit.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Override
    public GeneralResponse<List<UserEntity>> getAllUsers() {
        List<UserEntity> all = repository.findAll();

        if (all.isEmpty())
            return new GeneralResponse<>(false, -1, "Foydalanuvchilar topilmadi", null);
        return new GeneralResponse<>(true, 1, "success", all);
    }

    @Override
    public GeneralResponse<List<UserEntity>> getDoctors() {
        List<UserEntity> all = repository.findByRoleType(1);
        if (all.isEmpty())
            return new GeneralResponse<>(false, -1, "Foydalanuvchilar topilmadi", null);
        return new GeneralResponse<>(true, 1, "success", all);
    }

    @Override
    public GeneralResponse<UserEntity> getUserByPhone(String phoneNumber) {
        UserEntity byPhoneNumber = repository.findByPhoneNumber(phoneNumber);
        if (byPhoneNumber == null)
            return new GeneralResponse<>(false, -1, "Foydalanuvchilar topilmadi", null);
        return new GeneralResponse<>(true, 1, "success", byPhoneNumber);
    }

    @Override
    public GeneralResponse<UserEntity> getUserById(Long id) {
        Optional<UserEntity> byPhoneNumber = repository.findById(id);

        if (!byPhoneNumber.isPresent())
            return new GeneralResponse<>(false, -1, "Foydalanuvchilar topilmadi", null);
        return new GeneralResponse<>(true, 1, "success", byPhoneNumber.get());
    }

    @Override
    public GeneralResponse<UserEntity> saveUser(UserDto dto) {

        UserEntity byPhoneNumber = repository.findByPhoneNumber(dto.getPhoneNumber());
        if (byPhoneNumber != null)
            return new GeneralResponse<>(false, -1, "Bu raqam bilan foydalanuvchi mavjud", null);

        UserEntity entity = new UserEntity();

        entity.setFio(dto.getFio());
        entity.setUserName(dto.getUserName());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setIsActive(1);
        entity.setRoleType(dto.getRoleType());
        entity.setCreated_at(LocalDateTime.now());

        UserEntity save = repository.save(entity);
        return new GeneralResponse<>(true, 1, "success", save);
    }
}
