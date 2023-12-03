package com.example.medkit.model.entity;

import com.example.medkit.model.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "employees")
@Getter
@Setter
public class Employee extends BaseEntity {

    private String firstname;
    private String lastname;
    private String passportSerial;
    private String passportNumber;

    private String pinfl;

    @OneToMany
    private List<Image> images = new ArrayList<>();
}
