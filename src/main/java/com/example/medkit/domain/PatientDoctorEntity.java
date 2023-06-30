package com.example.medkit.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "MD_PATIENT_DOCTOR")
public class PatientDoctorEntity {

    @Id
    @Column(name = "ID", unique = true, nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "DOCTOR_ID")
    private Long doctorId;

    @Column(name = "PATIENT_ID")
    private Long patientId;
}
