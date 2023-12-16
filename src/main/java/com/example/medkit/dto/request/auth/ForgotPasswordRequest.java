package com.example.medkit.dto.request.auth;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ForgotPasswordRequest {
    @NotNull
    private String phoneNumber;
    @NotNull
    private String sessionKey;
    @NotNull
    private String password;
}
