package com.example.medkit.controller;


import com.example.medkit.dto.response.DoctorFilterDto;
import com.example.medkit.service.DoctorService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("/v1/doctor")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    @Operation(summary = "Doctor topish uchun filterlar ro'yhati(List)")
    @GetMapping("/get/filter")
    public ResponseEntity<?> getDoctorFilter() {
        try {
            List<DoctorFilterDto> filterDtos = Arrays.asList(
                    new DoctorFilterDto(1, "Dentist"),
                    new DoctorFilterDto(2, "Surgeon"),
                    new DoctorFilterDto(3, "Allergist"),
                    new DoctorFilterDto(4, "Urologist"),
                    new DoctorFilterDto(5, "Neorolig")
            );
            return ResponseEntity.ok(filterDtos);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
        }
    }

    @Operation(summary = "Filter bo'yicha doctorlarni olish")
    @GetMapping("/get/by-filter")
    public ResponseEntity<?> getDoctor(Integer code, String name, Integer expFrom, Integer expTo) {
        try {
            return ResponseEntity.ok(doctorService.getDoctorByFilter(code, name, expFrom, expTo));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
        }
    }

    @Operation(summary = "Rating bo'yicha doctorkar ro'yhatini olish")
    @GetMapping("/get/all/by-rating")
    public ResponseEntity<?> getDoctor() {
        try {
            return ResponseEntity.ok(doctorService.getAllDoctors());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
        }
    }

    @Operation(summary = "Telefon raqami bo'yicha doctorni olish")
    @GetMapping("/get/by/phone-number/{phoneNumber}")
    public ResponseEntity<?> getDoctorByPhoneNumber(@PathVariable(name = "phoneNumber") String phoneNumber) {
        try {
            return ResponseEntity.ok(doctorService.getDoctorByPhone(phoneNumber));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
        }
    }

    @Operation(summary = "Doctorni Profession bo'yicha qidirish")
    @GetMapping("/get/by/profession")
    public ResponseEntity<?> getDoctorByProfession(@RequestParam String prof) {
        try {
            return ResponseEntity.ok(doctorService.getDoctorByProfession(prof));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
        }
    }

    @Operation(summary = "Doctorni id raqami bo'yicha olish")
    @GetMapping("/get/by-doctor-id/{id}")
    public ResponseEntity<?> getDoctorById(@PathVariable(name = "id") Long id) {
        try {
            return ResponseEntity.ok(doctorService.getDoctorById(id));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
        }
    }


}
