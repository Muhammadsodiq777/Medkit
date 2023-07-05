package com.example.medkit.repository;

import com.example.medkit.domain.PatientEntity;
import com.example.medkit.domain.UserEntity;
import com.example.medkit.repository.custom.CustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<PatientEntity, Long>, CustomRepository {


}
