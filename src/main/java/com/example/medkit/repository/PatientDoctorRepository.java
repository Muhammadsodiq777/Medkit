package com.example.medkit.repository;

import com.example.medkit.domain.PatientDoctorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientDoctorRepository extends JpaRepository<PatientDoctorEntity, Long> {
}
