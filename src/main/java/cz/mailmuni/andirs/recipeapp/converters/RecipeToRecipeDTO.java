package cz.mailmuni.andirs.recipeapp.converters;

import cz.mailmuni.andirs.recipeapp.dto.RecipeDTO;
import cz.mailmuni.andirs.recipeapp.model.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RecipeToRecipeDTO implements Converter<Recipe, RecipeDTO> {

    private final NotesToNotesDTO notesConverter;
    private final CategoryToCategoryDTO categoryConverter;
    private final IngredientToIngredientDTO ingredientConverter;

    public RecipeToRecipeDTO(NotesToNotesDTO notesConverter, CategoryToCategoryDTO categoryConverter, IngredientToIngredientDTO ingredientConverter) {
        this.notesConverter = notesConverter;
        this.categoryConverter = categoryConverter;
        this.ingredientConverter = ingredientConverter;
    }

    @Synchronized
    @Override
    public RecipeDTO convert(Recipe source) {
        if (source == null) {
            return null;
        }

        RecipeDTO recipeDTO = new RecipeDTO();
        recipeDTO.setId(source.getId());
        recipeDTO.setCookTime(source.getCookTime());
        recipeDTO.setPrepTime(source.getPrepTime());
        recipeDTO.setDescription(source.getDescription());
        recipeDTO.setDifficulty(source.getDifficulty());
        recipeDTO.setDirections(source.getDirections());
        recipeDTO.setServings(source.getServings());
        recipeDTO.setSource(source.getSource());
        recipeDTO.setUrl(source.getUrl());
        recipeDTO.setImage(source.getImage());
        recipeDTO.setNotes(notesConverter.convert(source.getNotes()));

        source.getCategories()
                .forEach( category -> recipeDTO.getCategories().add(categoryConverter.convert(category)));

        source.getIngredients()
                .forEach(ingredient -> recipeDTO.getIngredients().add(ingredientConverter.convert(ingredient)));

        return recipeDTO;
    }
}
