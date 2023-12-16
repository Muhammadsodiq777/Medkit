package com.example.medkit.repository;

import com.example.medkit.model.entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Long> {

    Hospital findByAccountNumber(String accountNumber);
}