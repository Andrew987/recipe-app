package cz.mailmuni.andirs.recipeapp.dto;

import cz.mailmuni.andirs.recipeapp.model.Difficulty;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class RecipeDTO {
    private Long id;
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    private String directions;
    private Difficulty difficulty;
    private Byte[] image;
    private NotesDTO notes;

    private Set<CategoryDTO> categories = new HashSet<>();
    private Set<IngredientDTO> ingredients = new HashSet<>();
}
