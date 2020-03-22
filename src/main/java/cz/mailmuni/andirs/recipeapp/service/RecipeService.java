package cz.mailmuni.andirs.recipeapp.service;

import cz.mailmuni.andirs.recipeapp.model.Recipe;
import org.springframework.stereotype.Service;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getAllRecipes();
}
