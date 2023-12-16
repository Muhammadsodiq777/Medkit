package com.example.medkit.repository;

import com.example.medkit.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByPhoneNumber(String phoneNumber);
    User findByEmail(String email);

    boolean existsByEmail(String email);
    boolean existsByPhoneNumber(String phoneNumber);
}
