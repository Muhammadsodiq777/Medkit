package com.example.medkit.repository;

import com.example.medkit.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByPhoneNumber(String phoneNumber);

    List<UserEntity> findByRoleType(Integer role);

}
