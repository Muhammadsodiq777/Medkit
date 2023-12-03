package com.example.medkit.model.enums;

import com.example.medkit.exceptions.NotFoundException;
import lombok.Getter;

@Getter
public enum Role {

    SUPER_ADMIN(1),
    MANAGER(2),
    HOSPITAL(3),
    PATIENT(4);

    private final int id;

    Role(int id) {
        this.id = id;
    }

    public static Role getById(int id) {
        for (Role r: Role.values()){
            if(r.getId() == id){
                return r;
            }
        }
        throw new NotFoundException("Role Not Found");
    }

    public static Role getRole(String roleName) {
        for (Role r: Role.values()){
            if(roleName.equals(r.name())){
                return r;
            }
        }
        return null;
    }
}
