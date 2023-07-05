package com.example.medkit.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DoctorFilterDto {

    private Integer code;

    private String name;
}
