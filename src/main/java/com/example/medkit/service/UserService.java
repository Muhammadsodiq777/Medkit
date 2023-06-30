package com.example.medkit.service;

import com.example.medkit.domain.UserEntity;
import com.example.medkit.dto.GeneralResponse;
import com.example.medkit.dto.request.UserDto;

import java.util.List;

public interface UserService {

    GeneralResponse<List<UserEntity>> getAllUsers();

    GeneralResponse<UserEntity> getUserByPhone(String phoneNumber);

    GeneralResponse<UserEntity> getUserById(Long id);

    GeneralResponse<UserEntity> saveUser(UserDto dto);
}
