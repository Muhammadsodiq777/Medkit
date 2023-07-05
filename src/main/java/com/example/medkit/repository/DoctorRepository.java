package com.example.medkit.repository;

import com.example.medkit.domain.DoctorEntity;
import com.example.medkit.domain.UserEntity;
import com.example.medkit.repository.custom.CustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DoctorRepository extends JpaRepository<DoctorEntity,Long>, CustomRepository {
    Optional<DoctorEntity> findByPhoneNumber(String phoneNumber);

    @Query(value = "select * from md_doctor order by rating desc",nativeQuery = true)
    List<DoctorEntity> getAllDoctors();
    @Query(value = "select * from md_doctor md where md.profession like '%:prof%';",nativeQuery = true)
    List<DoctorEntity> getDoctorByProfession(String prof);

}
