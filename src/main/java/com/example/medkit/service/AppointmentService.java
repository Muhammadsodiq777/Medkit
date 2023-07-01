package com.example.medkit.service;

import com.example.medkit.domain.Appointment;
import com.example.medkit.dto.GeneralResponse;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentService {

    GeneralResponse<List<Appointment>> getAppointmentsByDoctorId(Long doctorId);
    GeneralResponse<List<Appointment>> getAppointmentsByPatientId(Long patientId);

    GeneralResponse<?> makeAppointment(String patientPhoneNumber, String doctorsPhoneNumber, LocalDateTime startDate, LocalDateTime endDate);

}
