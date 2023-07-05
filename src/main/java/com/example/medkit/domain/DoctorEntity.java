package com.example.medkit.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Random;

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

    @Column(name = "EXPERIENCE")
    private Long experience = new Random().nextLong(10) +1;

    @Column(name = "RATING")
    private Double rating = 3 + new Random().nextDouble() * (5 - 3);

    @Column(name = "ABOUT")
    private String about;

    @Column(name = "AVAILABLE")
    private String available = "08:00 am - 18:00 pm";

    @Column(name = "UNIVERSITY")
    private String university;

    @Column(name = "diploma")
    private String diploma;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "FULL_NAME")
    private String fio;

    @Column(name = "IS_ACTIVE", nullable = false)
    private Integer isActive; // 1 active | 0 not active

    @Column(name = "CREATED_AT")
    private LocalDateTime created_at;

}
