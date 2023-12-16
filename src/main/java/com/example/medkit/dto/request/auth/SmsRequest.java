package com.example.medkit.dto.request.auth;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SmsRequest {
    @NotNull
    private String phoneNumber;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String sessionKey;
}
