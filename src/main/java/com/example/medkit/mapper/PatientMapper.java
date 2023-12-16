package com.example.medkit.mapper;

import com.example.medkit.dto.PatientDto;
import com.example.medkit.model.entity.Patient;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PatientMapper {

    PatientDto toDto(Patient patient);
}
