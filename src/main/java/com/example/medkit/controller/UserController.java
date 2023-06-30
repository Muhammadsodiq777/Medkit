package com.example.medkit.controller;

import com.example.medkit.dto.request.UserDto;
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

    @GetMapping("/get/{phoneNumber}")
    public ResponseEntity<?> getUserByPhoneNumber(@PathVariable(name = "phoneNumber") String phoneNumber) {
        try {
           return ResponseEntity.ok(service.getUserByPhone(phoneNumber));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getUserById(@PathVariable(name = "id") Long id) {
        try {
           return ResponseEntity.ok(service.getAllUsers());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
        }
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveUser(@RequestBody UserDto dto) {
        try {
            return ResponseEntity.ok(service.saveUser(dto));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
        }
    }

}
