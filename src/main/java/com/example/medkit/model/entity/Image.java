package com.example.medkit.model.entity;

import com.example.medkit.model.base.BaseEntity;
import com.example.medkit.model.enums.ImageType;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "images")
@Getter
public class Image extends BaseEntity {
    private String contentType;
    private String url;
    private Long length;
    private String filename;

    @Enumerated(EnumType.STRING)
    private ImageType type;

    @ManyToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "hospital_id", referencedColumnName = "id")
    private Hospital hospital;

    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee employee;
}
