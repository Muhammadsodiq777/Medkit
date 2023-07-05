package com.example.medkit.service;

import com.example.medkit.domain.Recipes;
import com.example.medkit.dto.GeneralResponse;
import com.example.medkit.dto.RecipesDto;

import java.util.List;

public interface RecipesService {
    GeneralResponse<Recipes> addRecipes(RecipesDto dto);
    GeneralResponse<List<Recipes>> getRecipesByDoctorAndPatientId(Long doctorId, Long patientId);
    GeneralResponse<List<Recipes>> getPatientRecipe( Long patientId);

}
