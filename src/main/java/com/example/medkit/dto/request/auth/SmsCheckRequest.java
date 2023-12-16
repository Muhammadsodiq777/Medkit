package com.example.medkit.dto.request.auth;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class SmsCheckRequest {
    @NotNull
    private String phoneNumber;
    @NotNull
    private String sessionKey;
    private String code;
}
