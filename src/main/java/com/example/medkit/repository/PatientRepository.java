package com.example.medkit.repository;

import com.example.medkit.model.entity.Patient;
import com.example.medkit.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    Patient findByUser(User user);
}
