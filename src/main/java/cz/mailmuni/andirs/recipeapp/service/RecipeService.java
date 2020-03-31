package cz.mailmuni.andirs.recipeapp.service;

import cz.mailmuni.andirs.recipeapp.dto.RecipeDTO;
import cz.mailmuni.andirs.recipeapp.model.Recipe;

import java.util.Set;

public interface RecipeService {

    boolean exists(Long id);
    Set<Recipe> findAllRecipes();
    Recipe findById(Long id);
    RecipeDTO findDTOById(Long id);
    void deleteById(Long id);
    RecipeDTO saveRecipeDTO(RecipeDTO recipeDTO);

}
