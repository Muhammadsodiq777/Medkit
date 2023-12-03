package com.example.medkit.model.entity;

import com.example.medkit.model.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "roles")
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Role roleName;

    private String displayName;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Roles parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Roles> children = new ArrayList<>();

}
