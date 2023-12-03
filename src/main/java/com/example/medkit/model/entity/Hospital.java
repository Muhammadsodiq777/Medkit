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
@Table(name = "hospitals")
@Getter
@Setter
public class Hospital extends BaseEntity {
    private String ownerName;
    private String name;
    private String accountNumber;
    private String phoneNumber;
    private String email;
    private String password;

    @OneToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private User user;

    @OneToMany
    private List<Image> images = new ArrayList<>();

    @ManyToMany(mappedBy = "hospitals")
    private Set<Employee> employees = new HashSet<>();
}
