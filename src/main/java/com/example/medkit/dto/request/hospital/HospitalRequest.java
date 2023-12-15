package com.example.medkit.dto.request.hospital;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HospitalRequest {
    @NotNull
    private String name;
    private String firstname;
    private String lastname;
    @NotNull
    private String phoneNumber;
    @NotNull
    private String email;
    @NotNull
    private String password;

    @NotNull
    private String accountNumber;
}
