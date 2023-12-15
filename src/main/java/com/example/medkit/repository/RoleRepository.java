package com.example.medkit.repository;

import com.example.medkit.model.entity.Roles;
import com.example.medkit.model.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Roles, Long> {
    Roles findByRoleName(Role roleName);
}
