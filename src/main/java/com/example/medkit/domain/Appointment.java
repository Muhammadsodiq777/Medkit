package com.example.medkit.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "MD_APPOINTMENT")
public class Appointment {

    @Id
    @Column(name = "ID", unique = true, nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "DOCTOR_ID")
    private Long doctorId;

    @Column(name = "PATIENT_ID")
    private Long patientId;

    @Column(name = "STARTING_DATE")
    private String starting_date;

    @Column(name = "ENDING_DATE")
    private String ending_date;


}
