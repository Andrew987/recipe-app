package cz.mailmuni.andirs.recipeapp.dto;

import cz.mailmuni.andirs.recipeapp.model.Difficulty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class RecipeDTO {
    private Long id;

    @NotBlank
    @Size(min =3, max = 255)
    private String description;

    @Min(1)
    @Max(999)
    private Integer prepTime;

    @Min(1)
    @Max(999)
    private Integer cookTime;

    @Min(1)
    @Max(100)
    private Integer servings;

    @URL
    private String url;

    private String source;

    @NotBlank
    private String directions;

    private Difficulty difficulty;
    private Byte[] image;
    private NotesDTO notes;

    private Set<CategoryDTO> categories = new HashSet<>();
    private Set<IngredientDTO> ingredients = new HashSet<>();
}
