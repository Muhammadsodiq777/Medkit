package com.example.medkit.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientDto {
    private Long id;
    private String phoneNumber;
    private String firstname;
    private String lastname;
    private String parentName;
}
