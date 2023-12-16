package com.example.medkit.dto.request.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CheckResponse {
    private boolean exist;
    private String phoneNumber;
    private String sessionKey;
}
