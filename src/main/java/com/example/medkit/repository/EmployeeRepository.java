package com.example.medkit.repository;

import com.example.medkit.model.entity.Employee;
import com.example.medkit.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByUser(User user);
}
