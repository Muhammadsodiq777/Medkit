package com.example.medkit.repository;

import com.example.medkit.domain.DoctorEntity;
import com.example.medkit.domain.UserEntity;
import com.example.medkit.repository.custom.CustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DoctorRepository extends JpaRepository<DoctorEntity,Long>, CustomRepository {
    Optional<DoctorEntity> findByPhoneNumber(String phoneNumber);

}
