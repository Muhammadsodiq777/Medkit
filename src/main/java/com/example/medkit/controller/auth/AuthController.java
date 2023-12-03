package com.example.medkit.controller.auth;

import com.example.medkit.dto.request.auth.SmsRequest;
import com.example.medkit.dto.response.GeneralResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth/patient")
public class AuthController {

    @PostMapping("sms/ask")
    public ResponseEntity<GeneralResponse> smsAsk(@RequestBody SmsRequest request) {
        return ResponseEntity.ok(GeneralResponse.success(""));
    }
}
