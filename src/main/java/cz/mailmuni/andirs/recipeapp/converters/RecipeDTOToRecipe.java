package cz.mailmuni.andirs.recipeapp.converters;

import cz.mailmuni.andirs.recipeapp.dto.RecipeDTO;
import cz.mailmuni.andirs.recipeapp.model.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RecipeDTOToRecipe implements Converter<RecipeDTO, Recipe> {

    private final NotesDTOToNotes notesConverter;
    private final CategoryDTOToCategory categoryConverter;
    private final IngredientDTOToIngredient ingredientConverter;

    public RecipeDTOToRecipe(NotesDTOToNotes notesConverter, CategoryDTOToCategory categoryConverter, IngredientDTOToIngredient ingredientConverter) {
        this.notesConverter = notesConverter;
        this.categoryConverter = categoryConverter;
        this.ingredientConverter = ingredientConverter;
    }

    @Synchronized
    @Override
    public Recipe convert(RecipeDTO source) {
        if (source == null) {
            return null;
        }

        Recipe recipe = new Recipe();
        recipe.setId(source.getId());
        recipe.setCookTime(source.getCookTime());
        recipe.setPrepTime(source.getPrepTime());
        recipe.setDescription(source.getDescription());
        recipe.setDifficulty(source.getDifficulty());
        recipe.setDirections(source.getDirections());
        recipe.setServings(source.getServings());
        recipe.setSource(source.getSource());
        recipe.setUrl(source.getUrl());
        recipe.setImage(source.getImage());
        recipe.setNotes(notesConverter.convert(source.getNotes()));

        source.getCategories()
                .forEach( category -> recipe.getCategories().add(categoryConverter.convert(category)));

        source.getIngredients()
                .forEach(ingredient -> recipe.getIngredients().add(ingredientConverter.convert(ingredient)));

        return recipe;
    }
}
