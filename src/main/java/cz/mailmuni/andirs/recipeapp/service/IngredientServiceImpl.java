package cz.mailmuni.andirs.recipeapp.service;

import cz.mailmuni.andirs.recipeapp.converters.IngredientDTOToIngredient;
import cz.mailmuni.andirs.recipeapp.converters.IngredientToIngredientDTO;
import cz.mailmuni.andirs.recipeapp.dto.IngredientDTO;
import cz.mailmuni.andirs.recipeapp.model.Ingredient;
import cz.mailmuni.andirs.recipeapp.repositories.IngredientRepository;
import org.springframework.stereotype.Service;

@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;
    private final IngredientToIngredientDTO ingredientToIngredientDTO;
    private final IngredientDTOToIngredient ingredientDTOToIngredient;

    public IngredientServiceImpl(IngredientRepository ingredientRepository, IngredientToIngredientDTO ingredientToIngredientDTO, IngredientDTOToIngredient ingredientDTOToIngredient) {
        this.ingredientRepository = ingredientRepository;
        this.ingredientToIngredientDTO = ingredientToIngredientDTO;
        this.ingredientDTOToIngredient = ingredientDTOToIngredient;
    }

    @Override
    public Ingredient findById(Long id) {
        return ingredientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ingredient not found"));
    }

    @Override
    public IngredientDTO saveIngredientDTO(IngredientDTO ingredientDTO) {
        Ingredient ingredient = ingredientDTOToIngredient.convert(ingredientDTO);
        Ingredient savedIngredient = ingredientRepository.save(ingredient);
        return ingredientToIngredientDTO.convert(savedIngredient);
    }

    @Override
    public void deleteById(Long id) {
        ingredientRepository.deleteById(id);
    }

    @Override
    public IngredientDTO findDTOById(Long id) {
        return ingredientToIngredientDTO.convert(findById(id));
    }
}
