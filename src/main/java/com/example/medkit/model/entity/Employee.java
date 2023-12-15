package com.example.medkit.model.entity;

import com.example.medkit.model.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @OneToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private User user;

    @OneToMany
    private List<Image> images = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "hospital_employees",
            joinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "hospital_id",  referencedColumnName = "id")
    )
    private Set<Hospital> hospitals = new HashSet<>();
}
