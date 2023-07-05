package com.example.medkit.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DoctorDto {

    private String fio;

    private String phoneNumber;

    private String userName;

    private String university;

    private String diploma;
}
