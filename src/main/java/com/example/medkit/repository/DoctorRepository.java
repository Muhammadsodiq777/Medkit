package com.example.medkit.repository;

import com.example.medkit.domain.DoctorEntity;
import com.example.medkit.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DoctorRepository extends JpaRepository<DoctorEntity,Long> {
    Optional<DoctorEntity> findByPhoneNumber(String phoneNumber);

    @Query(value = "select * from md_doctor order by rating desc",nativeQuery = true)
    List<DoctorEntity> getAllDoctors();

    @Query(value = "select * from md_doctor where profession" +
            " like '%:name%' and experience between :expFrom and :expTo",nativeQuery = true)
    List<DoctorEntity> getDoctorByFilter(String name, Integer expFrom, Integer expTo);

    @Query(value = "select * from md_doctor md where md.profession like '%:prof%';",nativeQuery = true)
    List<DoctorEntity> getDoctorByProfession(String prof);

}
