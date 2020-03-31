package cz.mailmuni.andirs.recipeapp.converters;

import cz.mailmuni.andirs.recipeapp.dto.IngredientDTO;
import cz.mailmuni.andirs.recipeapp.model.Ingredient;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IngredientToIngredientDTO implements Converter<Ingredient, IngredientDTO> {

    private final UnitOfMeasureToUnitOfMeasureDTO uomConverter;

    public IngredientToIngredientDTO(UnitOfMeasureToUnitOfMeasureDTO uomConverter) {
        this.uomConverter = uomConverter;
    }

    @Synchronized
    @Override
    public IngredientDTO convert(Ingredient source) {
        if (source == null) {
            return null;
        }

        IngredientDTO ingredient = new IngredientDTO();
        ingredient.setId(source.getId());
        ingredient.setRecipeId(source.getRecipe().getId());
        ingredient.setAmount(source.getAmount());
        ingredient.setDescription(source.getDescription());
        ingredient.setUom(uomConverter.convert(source.getUom()));

        return ingredient;
    }
}
