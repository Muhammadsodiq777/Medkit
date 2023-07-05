package com.example.medkit.controller;

import com.example.medkit.dto.request.PatientDto;
import com.example.medkit.dto.request.DoctorDto;
import com.example.medkit.service.DoctorService;
import com.example.medkit.service.PatientService;
import com.example.medkit.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;
    private final PatientService patientService;
    private final DoctorService doctorService;

    @Operation(summary = "Doctor larni saqalsh uchun api")
    @PostMapping("/save/doctor") // 1 = DOCTOR | 0 = PATIENT
    public ResponseEntity<?> saveDoctor(@RequestBody DoctorDto doctorDto) {
        try {
                return ResponseEntity.ok(doctorService.saveDoctor(doctorDto));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
        }
    }

    @Operation(summary = "Patient larni saqalsh uchun api")
    @PostMapping("/save/user") // 1 = DOCTOR | 0 = PATIENT
    public ResponseEntity<?> saveUser(@RequestBody PatientDto patientDto) {
        try {
                return ResponseEntity.ok(patientService.savePatient(patientDto));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
        }
    }

}
