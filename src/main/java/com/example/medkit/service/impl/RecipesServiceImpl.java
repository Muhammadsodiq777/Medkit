package com.example.medkit.service.impl;

import com.example.medkit.domain.Recipes;
import com.example.medkit.dto.GeneralResponse;
import com.example.medkit.dto.RecipesDto;
import com.example.medkit.repository.RecipesRepository;
import com.example.medkit.service.RecipesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecipesServiceImpl implements RecipesService {

    private final RecipesRepository repository;

    @Override
    public GeneralResponse<Recipes> addRecipes(RecipesDto dto) {
        Recipes recipes = new Recipes();

        recipes.setName(dto.getName());
        recipes.setTimesInDay(dto.getTimes());
        recipes.setDuration(dto.getDuration());
        recipes.setDoctorId(dto.getDoctorId());
        recipes.setPatientId(dto.getPatientId());
        recipes.setCreated_at(LocalDateTime.now());
        Recipes save = repository.save(recipes);

        return new GeneralResponse<>(true, 1, "success", save);
    }

    @Override
    public GeneralResponse<List<Recipes>> getRecipesByDoctorAndPatientId(Long doctorId, Long patientId) {
        List<Recipes> recipesByDoctorAndUserId = repository.getRecipesByDoctorAndUserId(doctorId, patientId);
        if (recipesByDoctorAndUserId.isEmpty())
            return new GeneralResponse<>(false, -1, "Malumot topilmadi!!!", null);
        return new GeneralResponse<>(true, 1, "success", recipesByDoctorAndUserId);
    }

    @Override
    public GeneralResponse<List<Recipes>> getPatientRecipe(Long patientId) {
        List<Recipes> recipesByDoctorAndUserId = repository.getRecipesByPatientId(patientId);
        if (recipesByDoctorAndUserId.isEmpty())
            return new GeneralResponse<>(false, -1, "Malumot topilmadi!!!", null);
        return new GeneralResponse<>(true, 1, "success", recipesByDoctorAndUserId);    }
}
