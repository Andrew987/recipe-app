package cz.mailmuni.andirs.recipeapp.dto;


import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class IngredientDTO {
    private long id;
    private Long recipeId;
    private String description;
    private BigDecimal amount;
    private UnitOfMeasureDTO uom;

}
