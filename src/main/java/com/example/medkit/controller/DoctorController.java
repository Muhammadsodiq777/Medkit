package com.example.medkit.controller;


import com.example.medkit.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1/doctor")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    @GetMapping("/get/all")
    public ResponseEntity<?> getDoctor() {
        try {
            return ResponseEntity.ok(doctorService.getAllDoctors());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
        }
    }

    @GetMapping("/get/by/phone-number/{phoneNumber}")
    public ResponseEntity<?> getDoctorByPhoneNumber(@PathVariable(name = "phoneNumber") String phoneNumber) {
        try {
            return ResponseEntity.ok(doctorService.getDoctorByPhone(phoneNumber));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
        }
    }

    @GetMapping("/get/by-doctor-id/{id}")
    public ResponseEntity<?> getDoctorById(@PathVariable(name = "id") Long id) {
        try {
            return ResponseEntity.ok(doctorService.getDoctorById(id));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
        }
    }


}
