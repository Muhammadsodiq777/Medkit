package com.example.medkit.controller;

import com.example.medkit.dto.RecipesDto;
import com.example.medkit.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService service;

    @GetMapping("/get/all")
    public ResponseEntity<?> getPatientsList() {
        try {
            return ResponseEntity.ok(service.getPatientUsers());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
        }
    }

    @GetMapping("/get/by/{id}")
    public ResponseEntity<?> getPatientsList(@PathVariable(name = "id") Long id) {
        try {
            return ResponseEntity.ok(service.getPatientById(id));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
        }
    }
}
