package com.example.medkit.repository;

import com.example.medkit.domain.Recipes;
import com.example.medkit.repository.custom.CustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipesRepository extends JpaRepository<Recipes, Long>, CustomRepository {

    List<Recipes> getRecipesByPatientId(Long id);
}
