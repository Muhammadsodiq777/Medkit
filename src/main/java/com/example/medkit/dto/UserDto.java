package com.example.medkit.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private Long id;
    private String email;
    private String phoneNumber;
    private String password;

    public UserDto() {
    }

    public UserDto(String email, String phoneNumber, String password) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public UserDto(Long id, String email, String phoneNumber, String password) {
        this.id = id;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }
}
