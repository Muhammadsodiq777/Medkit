package com.example.medkit.services.employee;

import com.example.medkit.dto.request.hospital.HospitalRequest;
import com.example.medkit.model.entity.Employee;
import com.example.medkit.model.entity.Hospital;
import com.example.medkit.model.entity.User;

public interface EmployeeService {
    Employee saveEmployee(User user, Hospital hospital, HospitalRequest request);
}
