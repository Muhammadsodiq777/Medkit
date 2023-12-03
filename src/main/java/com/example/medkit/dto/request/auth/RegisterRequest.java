package com.example.medkit.dto.request.auth;


import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {
    @NotNull
    private String phoneNumber;
    private String firstname;
    private String lastname;
    private String surname;
    @NotNull
    private String password;
}
