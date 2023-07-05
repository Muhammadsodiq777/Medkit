package com.example.medkit.repository;

import com.example.medkit.domain.Recipes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipesRepository extends JpaRepository<Recipes, Long> {

    @Query(value = "select * from md_recipes md where md.doctor_id = :doctorId and md.patient_id = :patientId",nativeQuery = true)
    List<Recipes> getRecipesByDoctorAndUserId(Long doctorId, Long patientId);

    List<Recipes> getRecipesByPatientId(Long id);
}
