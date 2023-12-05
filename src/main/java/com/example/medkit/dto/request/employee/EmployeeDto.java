package com.example.medkit.dto.request.employee;

import com.example.medkit.dto.request.hospital.HospitalDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class EmployeeDto {
    private Long id;
    private String firstname;
    private String lastname;
    private String phoneNumber;
    private Set<HospitalDto> hospitals;
}
