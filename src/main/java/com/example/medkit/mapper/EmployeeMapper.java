package com.example.medkit.mapper;

import com.example.medkit.dto.request.employee.EmployeeDto;
import com.example.medkit.model.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    EmployeeDto toDto(Employee employee);
}
