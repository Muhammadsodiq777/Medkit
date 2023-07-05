package com.example.medkit.repository;

import com.example.medkit.domain.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface MeetingRepository extends JpaRepository<Meeting, Long> {

    List<Meeting> findAllByDoctorIdOrPatientId(Long id, Long patientId);
}
