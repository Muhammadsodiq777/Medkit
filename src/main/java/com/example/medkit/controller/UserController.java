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

    @Operation(summary = "Doctor va Patient larni saqalsh uchun api", description = "Doctor uchun type = 1 | Patient uchun type = 0")
    @PostMapping("/save") // 1 = DOCTOR | 0 = PATIENT
    public ResponseEntity<?> saveUser(@RequestParam Integer type, DoctorDto doctorDto, PatientDto patientDto) {
        try {
            if (type == 1)
                return ResponseEntity.ok(doctorService.saveDoctor(doctorDto));
            else
                return ResponseEntity.ok(patientService.savePatient(patientDto));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
        }
    }

}
