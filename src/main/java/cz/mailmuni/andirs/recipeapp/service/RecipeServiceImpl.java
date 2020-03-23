package cz.mailmuni.andirs.recipeapp.service;

import cz.mailmuni.andirs.recipeapp.model.Recipe;
import cz.mailmuni.andirs.recipeapp.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Set<Recipe> getAllRecipes() {
        log.debug("I AM IN THE SERVICE!!! Yeah!!!");

        Set<Recipe> recipes = new HashSet<>();
        recipeRepository.findAll().forEach(recipe -> recipes.add(recipe));
        return recipes;
    }
}
