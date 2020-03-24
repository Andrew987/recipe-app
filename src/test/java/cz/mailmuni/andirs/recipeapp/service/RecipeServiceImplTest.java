package cz.mailmuni.andirs.recipeapp.service;

import cz.mailmuni.andirs.recipeapp.model.Recipe;
import cz.mailmuni.andirs.recipeapp.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class RecipeServiceImplTest {

    private RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository);
    }

    @Test
    void getAllRecipes() {
        Set<Recipe> recipesData = new HashSet<>();
        recipesData.add(new Recipe());
        when(recipeRepository.findAll()).thenReturn(recipesData);

        Set<Recipe> recipes = recipeService.getAllRecipes();

        assertEquals(1, recipes.size());
        verify(recipeRepository, times(1)).findAll();
    }
}