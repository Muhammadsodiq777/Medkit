package com.example.medkit.controller;

import com.example.medkit.dto.RecipesDto;
import com.example.medkit.service.RecipesService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/recipes")
@RequiredArgsConstructor
public class RecipesController {

    private final RecipesService service;

    @Operation(summary = "Retsep qo'shish")
    @PostMapping("/add")
    public ResponseEntity<?> addRecipe(@RequestBody RecipesDto dto) {
        try {
                return ResponseEntity.ok(service.addRecipes(dto));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
        }
    }

    @Operation(summary = "Doctor va bemor idlari orqali retseplarni olish", description = "doctor pagesida krk bo'ladi")
    @GetMapping("/get-recipes")
    public ResponseEntity<?> getRecipeDoctorAndPatientId(@RequestParam Long doctorId, @RequestParam Long patientId) {
        try {
                return ResponseEntity.ok(service.getRecipesByDoctorAndPatientId(doctorId, patientId));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
        }
    }

    @Operation(summary = "Bemorning dorilar ro'yhatini olish", description = "bemor id raqami beriladi(majburiy)")
    @GetMapping("/get/patient-recipes")
    public ResponseEntity<?> getPatientRecipes(@RequestParam Long patientId) {
        try {
                return ResponseEntity.ok(service.getPatientRecipe(patientId));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
        }
    }

}
