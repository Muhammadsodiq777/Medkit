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
@Entity(name = "MD_PATIENTS")
public class PatientEntity {

    @Id
    @Column(name = "ID", unique = true, nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "PHONE_NUMBER", nullable = false)
    private String phoneNumber;

    @Column(name = "FULL_NAME", nullable = false)
    private String fio;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "IS_ACTIVE", nullable = false)
    private Integer isActive; // 1 active | 0 not active

    @Column(name = "ROLE_TYPE")
    private Integer roleType; // 1 doctor | 0 patient

    @Column(name = "CREATED_AT")
    private LocalDateTime created_at;

    @Column(name = "UPDATED_AT")
    private LocalDateTime UPDATED_AT;
}
