package com.example.medkit.mapper;

import com.example.medkit.dto.request.hospital.HospitalDto;
import com.example.medkit.model.entity.Hospital;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface HospitalMapper {

    HospitalDto toDto(Hospital hospital);

    Set<HospitalDto> toListDto(Set<Hospital> hospitals);
}
