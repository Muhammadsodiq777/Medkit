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
@Entity(name = "MD_MEETING")
public class Meeting {

    @Id
    @Column(name = "ID", unique = true, nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "MEETING_DATE")
    private LocalDateTime meeting_date;

    @Column(name = "DOCTOR_ID")
    private Long doctorId;

    @Column(name = "PATIENT_ID")
    private Long patientId;

}
