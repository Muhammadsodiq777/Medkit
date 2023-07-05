package com.example.medkit.service.impl;

import com.example.medkit.domain.Appointment;
import com.example.medkit.domain.DoctorEntity;
import com.example.medkit.domain.PatientEntity;
import com.example.medkit.dto.GeneralResponse;
import com.example.medkit.repository.AppointmentRepository;
import com.example.medkit.repository.DoctorRepository;
import com.example.medkit.repository.PatientRepository;
import com.example.medkit.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    @Override
    public GeneralResponse<List<Appointment>> getAppointmentsById(Long doctorId) {
        List<Appointment> appointmentsByDoctorId = appointmentRepository.getAppointmentsByDoctorId(doctorId);

        if (appointmentsByDoctorId.isEmpty()) {
            List<Appointment> aaa = appointmentRepository.getAppointmentByPatientId(doctorId);
            if(aaa.isEmpty())
                return new GeneralResponse<>(false, -1, "Appointment topilmadi", null);
            return new GeneralResponse<>(true, 1, "success", appointmentsByDoctorId);
        }
        return new GeneralResponse<>(true, 1, "success", appointmentsByDoctorId);

    }
    @Override
    public GeneralResponse<?> makeAppointment(String patientPhoneNumber, String doctorsPhoneNumber, String startDate, String endDate) {

        Optional<PatientEntity> patient = patientRepository.findByPhoneNumber(patientPhoneNumber);
        Optional<DoctorEntity> doctor = doctorRepository.findByPhoneNumber(doctorsPhoneNumber);
        if (patient.isPresent() && doctor.isPresent()){
            Appointment appointment= new Appointment();
            appointment.setPatientId(patient.get().getId());
            appointment.setDoctorId(doctor.get().getId());
            appointment.setStarting_date(startDate);
            appointment.setEnding_date(endDate);
            Appointment savedAppointment = appointmentRepository.save(appointment);
            return new GeneralResponse<>(true, 1, "Appointment tasdiqlandi", savedAppointment.getId());
        }

        return new GeneralResponse<>(false, -1, "Foydalanuvchilar yoki Doctor topilmadi", null);

    }

}
