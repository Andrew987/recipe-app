package cz.mailmuni.andirs.recipeapp.service;

import cz.mailmuni.andirs.recipeapp.dto.IngredientDTO;
import cz.mailmuni.andirs.recipeapp.model.Ingredient;

public interface IngredientService {
    IngredientDTO findDTOById(Long id);
    Ingredient findById(Long id);
    IngredientDTO saveIngredientDTO(IngredientDTO ingredientDTO);
    void deleteById(Long id);
}
