package cz.mailmuni.andirs.recipeapp.converters;

import cz.mailmuni.andirs.recipeapp.dto.IngredientDTO;
import cz.mailmuni.andirs.recipeapp.model.Ingredient;
import cz.mailmuni.andirs.recipeapp.model.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IngredientDTOToIngredient implements Converter<IngredientDTO, Ingredient> {

    private final UnitOfMeasureDTOToUnitOfMeasure uomConverter;

    public IngredientDTOToIngredient(UnitOfMeasureDTOToUnitOfMeasure uomConverter) {
        this.uomConverter = uomConverter;
    }

    @Synchronized
    @Override
    public Ingredient convert(IngredientDTO source) {
        if (source == null) {
            return null;
        }

        Ingredient ingredient = new Ingredient();
        ingredient.setId(source.getId());
        ingredient.setAmount(source.getAmount());
        ingredient.setDescription(source.getDescription());
        ingredient.setUom(uomConverter.convert(source.getUom()));

        if(source.getRecipeId() != null){
            Recipe recipe = new Recipe();
            recipe.setId(source.getRecipeId());
            ingredient.setRecipe(recipe);
            recipe.addIngredient(ingredient);
        }

        return ingredient;
    }
}
