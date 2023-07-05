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
@Entity(name = "MD_RECIPES")
public class Recipes {

    @Id
    @Column(name = "ID", unique = true, nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "TIMES")
    private Integer times;

    @Column(name = "DOCTOR_ID")
    private Long doctorId;

    @Column(name = "PATIENT_ID")
    private Long patientId;

    @Column(name = "DURATION")
    private Integer duration; // in days

    @Column(name = "TIMES_IN_DAY")
    private String timesInDay;

    @Column(name = "CREATED_AT")
    private LocalDateTime created_at;
}
