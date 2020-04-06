package cz.mailmuni.andirs.recipeapp.service;

import cz.mailmuni.andirs.recipeapp.converters.RecipeDTOToRecipe;
import cz.mailmuni.andirs.recipeapp.converters.RecipeToRecipeDTO;
import cz.mailmuni.andirs.recipeapp.dto.RecipeDTO;
import cz.mailmuni.andirs.recipeapp.exceptions.NotFoundException;
import cz.mailmuni.andirs.recipeapp.model.Recipe;
import cz.mailmuni.andirs.recipeapp.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeDTOToRecipe recipeDTOToRecipe;
    private final RecipeToRecipeDTO recipeToRecipeDTO;

    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeDTOToRecipe recipeDTOToRecipe, RecipeToRecipeDTO recipeToRecipeDTO) {
        this.recipeRepository = recipeRepository;
        this.recipeDTOToRecipe = recipeDTOToRecipe;
        this.recipeToRecipeDTO = recipeToRecipeDTO;
    }

    @Override
    public boolean exists(Long id) {
        return recipeRepository.existsById(id);
    }

    @Override
    public Set<Recipe> findAllRecipes() {
        Set<Recipe> recipes = new HashSet<>();
        recipeRepository.findAll().forEach(recipe -> recipes.add(recipe));
        return recipes;
    }

    @Override
    public Recipe findById(Long id) {
        return recipeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Recipe not found for ID value: " + id));
    }

    @Override
    @Transactional
    public RecipeDTO findDTOById(Long id) {
        return recipeToRecipeDTO.convert(findById(id));
    }

    @Override
    public void deleteById(Long id) {
        recipeRepository.deleteById(id);
    }

    @Override
    @Transactional
    public RecipeDTO saveRecipeDTO(RecipeDTO recipeDTO) {
        Recipe recipe = recipeDTOToRecipe.convert(recipeDTO);
        recipeRepository.save(recipe);
        return recipeToRecipeDTO.convert(recipe);
    }
}
