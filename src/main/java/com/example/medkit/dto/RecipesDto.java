package com.example.medkit.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecipesDto {

    private String name;

    private String times;

    private Integer duration;

    private Long doctorId;

    private Long patientId;
}
