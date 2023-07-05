package com.example.medkit.service;

import com.example.medkit.domain.Appointment;
import com.example.medkit.dto.GeneralResponse;

import java.util.List;

public interface AppointmentService {

    GeneralResponse<List<Appointment>> getAppointmentsById(Long doctorId);

    GeneralResponse<?> makeAppointment(String patientPhoneNumber, String doctorsPhoneNumber, String startDate, String endDate);

}
