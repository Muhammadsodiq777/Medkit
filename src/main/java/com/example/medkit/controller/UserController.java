package com.example.medkit.controller;

import com.example.medkit.dto.request.PatientDto;
import com.example.medkit.dto.request.DoctorDto;
import com.example.medkit.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping("/get/all")
    public ResponseEntity<?> getAllUsers() {
        try {
            return ResponseEntity.ok(service.getAllUsers());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
        }
    }

    @GetMapping("/get/all-doctors")
    public ResponseEntity<?> getAllDoctors() {
        try {
            return ResponseEntity.ok(service.getDoctors());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
        }
    }

    @GetMapping("/get/by/phone-number/{phoneNumber}")
    public ResponseEntity<?> getUserByPhoneNumber(@PathVariable(name = "phoneNumber") String phoneNumber) {
        try {
            return ResponseEntity.ok(service.getUserByPhone(phoneNumber));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
        }
    }

    @GetMapping("/get/by-user-id/{id}")
    public ResponseEntity<?> getUserById(@PathVariable(name = "id") Long id) {
        try {
            return ResponseEntity.ok(service.getAllUsers());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
        }
    }

    @PostMapping("/save") // 1 = DOCTOR | 0 = PATIENT
    public ResponseEntity<?> saveUser(@RequestParam Integer type, DoctorDto dto, PatientDto patientDto) {
        try {
            if (type == 1)
                return ResponseEntity.ok(service.saveUser(dto));
            else
                return ResponseEntity.ok(service.saveUser(dto));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
        }
    }

}
