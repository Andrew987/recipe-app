package cz.mailmuni.andirs.recipeapp.controllers;

import cz.mailmuni.andirs.recipeapp.model.Recipe;
import cz.mailmuni.andirs.recipeapp.service.RecipeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class RecipeControllerTest {

    @InjectMocks
    RecipeController recipeController;

    @Mock
    RecipeService recipeService;

    @Test
    void showRecipe() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setId(1L);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
        when(recipeService.findById(1L)).thenReturn(recipe);

        mockMvc.perform(get("/recipe/show/1"))
            .andExpect(status().isOk())
            .andExpect(model().attribute("recipe", recipe))
            .andExpect(view().name("recipe/show"));
    }
}