package cz.mailmuni.andirs.recipeapp.repositories;

import cz.mailmuni.andirs.recipeapp.model.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
