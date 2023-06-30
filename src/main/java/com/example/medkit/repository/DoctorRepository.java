package com.example.medkit.repository;

import com.example.medkit.domain.DoctorEntity;
import com.example.medkit.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DoctorRepository extends JpaRepository<DoctorEntity,Long> {
    Optional<DoctorEntity> findByPhoneNumber(String phoneNumber);
}
