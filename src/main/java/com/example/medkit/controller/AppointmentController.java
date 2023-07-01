package com.example.medkit.controller;

import com.example.medkit.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/v1/appointment")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;
    @GetMapping("/get/by/doctor-id/{doctorId}")
    public ResponseEntity<?> getAppointmentsByDoctorId(@PathVariable (name = "doctorId") Long doctorId) {
        try {
            return ResponseEntity.ok(appointmentService.getAppointmentsByDoctorId(doctorId));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
        }
    }
    @GetMapping("/get/by/doctor-id/{patientId}")
    public ResponseEntity<?> getAppointmentsByPatientId(@PathVariable (name = "patientId") Long patientId) {
        try {
            return ResponseEntity.ok(appointmentService.getAppointmentsByPatientId(patientId));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
        }
    }

    @GetMapping("/makeApponment")
    public ResponseEntity<?> makeAppointment(@RequestParam String patientPhoneNumber, String doctorsPhoneNumber, LocalDateTime startDate, LocalDateTime endDate){
        try {
            return ResponseEntity.ok(appointmentService.makeAppointment(patientPhoneNumber,doctorsPhoneNumber,startDate,endDate));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
        }
    }


}
