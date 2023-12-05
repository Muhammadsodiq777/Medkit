package com.example.medkit.dto.request.hospital;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HospitalDto {
    private Long id;
    private String ownerName;
    private String name;
    private String accountNumber;
    private String phoneNumber;
    private String email;
}
