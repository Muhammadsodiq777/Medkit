package com.example.medkit.dto.request.auth;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeLoginRequest {
    @NotNull
    private String phoneNumber;
    @NotNull
    private String password;
}
