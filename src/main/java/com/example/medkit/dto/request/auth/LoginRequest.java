package com.example.medkit.dto.request.auth;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    @NotNull
    private String phoneNumber;
    @NotNull
    private String password;
    @NotNull
    private String sessionKey;
}
