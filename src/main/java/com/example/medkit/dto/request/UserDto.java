package com.example.medkit.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private String fio;

    private String phoneNumber;

    private String userName;

    private Integer roleType;
}
