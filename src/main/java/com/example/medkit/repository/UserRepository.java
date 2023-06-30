package com.example.medkit.repository;

import com.example.medkit.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByPhoneNumber(String phoneNumber);

}
