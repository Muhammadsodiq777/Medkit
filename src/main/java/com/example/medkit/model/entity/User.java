package com.example.medkit.model.entity;

import com.example.medkit.model.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User extends BaseEntity {
    private String phoneNumber;
    private String email;
    private String password;
    private String type;
}
