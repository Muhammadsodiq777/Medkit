package com.example.medkit.services.employee.impl;

import com.example.medkit.dto.request.hospital.HospitalRequest;
import com.example.medkit.model.entity.Employee;
import com.example.medkit.model.entity.Hospital;
import com.example.medkit.model.entity.User;
import com.example.medkit.repository.EmployeeRepository;
import com.example.medkit.services.employee.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final EmployeeRepository employeeRepository;

    @Override
    public Employee saveEmployee(User user, Hospital hospital, HospitalRequest request) {
        Employee employee = new Employee();
        employee.setFirstname(request.getFirstname());
        employee.setUser(user);
        employee.setLastname(request.getLastname());
        Set<Hospital> hospitals = new HashSet<>();
        hospitals.add(hospital);
        employee.setHospitals(hospitals);
        employeeRepository.save(employee);
        return employee;
    }
}
