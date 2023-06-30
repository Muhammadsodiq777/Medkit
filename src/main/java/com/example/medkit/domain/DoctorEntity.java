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
@Entity(name = "MD_DOCTOR")
public class DoctorEntity {

    @Id
    @Column(name = "ID", unique = true, nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "PROFESSION")
    private String profession;

    @Column(name = "PATIENT")
    private Long patient;

    @Column(name = "EXPERIENCE")
    private Long experience;

    @Column(name = "RATING")
    private Double rating;

    @Column(name = "ABOUT")
    private String about;

    @Column(name = "PHONE_NUMBER", nullable = false)
    private String phoneNumber;

    @Column(name = "FULL_NAME", nullable = false)
    private String fio;

    @Column(name = "IS_ACTIVE", nullable = false)
    private Integer isActive; // 1 active | 0 not active

    @Column(name = "CREATED_AT")
    private LocalDateTime created_at;

}
