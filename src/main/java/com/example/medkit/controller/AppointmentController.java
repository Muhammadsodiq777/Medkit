package com.example.medkit.controller;

import com.example.medkit.service.AppointmentService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/appointment")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @Operation(summary = "Doctor id raqami berilsa uning appointmentlarini olish")
    @GetMapping("/get/by/{id}")
    public ResponseEntity<?> getAppointmentsByDoctorId(@PathVariable (name = "id") Long id) {
        try {
            return ResponseEntity.ok(appointmentService.getAppointmentsById(id));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
        }
    }

    @Operation(summary = "Yangi appointment belgilash", description = "yyyy-MM-dd-HH-mm Start date shu ko'rinishda bo'lsa yaxshi bo'lar edi")
    @GetMapping("/make-appointment")
    public ResponseEntity<?> makeAppointment( String patientPhoneNumber, String doctorsPhoneNumber, String startDate, String endDate){
        try {
            return ResponseEntity.ok(appointmentService.makeAppointment(patientPhoneNumber,doctorsPhoneNumber,startDate,endDate));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
        }
    }


}
