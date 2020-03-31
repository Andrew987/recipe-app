package cz.mailmuni.andirs.recipeapp.service;

import cz.mailmuni.andirs.recipeapp.model.Recipe;
import cz.mailmuni.andirs.recipeapp.repositories.RecipeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RecipeServiceImplTest {

    @Mock
    RecipeRepository recipeRepository;

    @InjectMocks
    RecipeServiceImpl recipeService;

    @Test
    void getAllRecipes() {
        Set<Recipe> recipesData = new HashSet<>();
        recipesData.add(new Recipe());
        when(recipeRepository.findAll()).thenReturn(recipesData);

        Set<Recipe> recipes = recipeService.findAllRecipes();

        assertEquals(1, recipes.size());
        verify(recipeRepository, times(1)).findAll();
    }

    @Test
    void getRecipeByIdTest() {
        Long testId = 2L;
        Recipe recipe = new Recipe();
        recipe.setId(testId);
        when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(recipe));

        Recipe recipeReturned = recipeService.findById(testId);

        assertEquals(testId, recipeReturned.getId());
        verify(recipeRepository).findById(testId);
        verify(recipeRepository, never()).findAll();
    }
}