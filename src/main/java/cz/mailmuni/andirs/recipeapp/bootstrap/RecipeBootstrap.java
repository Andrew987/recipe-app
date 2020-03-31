package cz.mailmuni.andirs.recipeapp.bootstrap;

import cz.mailmuni.andirs.recipeapp.model.*;
import cz.mailmuni.andirs.recipeapp.repositories.RecipeRepository;
import cz.mailmuni.andirs.recipeapp.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Slf4j
@Component
public class RecipeBootstrap implements CommandLineRunner {

    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final RecipeRepository recipeRepository;

    public RecipeBootstrap(UnitOfMeasureRepository unitOfMeasureRepository, RecipeRepository recipeRepository) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.recipeRepository = recipeRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        log.debug("Loading bootstrap data ...");
        loadData();
        log.debug("Finished loading bootstrap data");
    }

    private void loadData() {
        recipeRepository.save(getGuacamole());
    }

    private Recipe getGuacamole() {
        Recipe guacamole = new Recipe();
        guacamole.setDescription("Perfect Guacamole");
        guacamole.setPrepTime(10);
        guacamole.setCookTime(0);
        guacamole.setDifficulty(Difficulty.EASY);
        guacamole.setSource("Simply Recipes");
        guacamole.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");

        addIngredient(guacamole, "avocado", 2, "Piece");
        addIngredient(guacamole, "salt", 1f/4, "Teaspoon");
        addIngredient(guacamole, "fresh lime juice or lemon juice", 1, "Tablespoon");
        addIngredient(guacamole, "minced red onion or thinly sliced green onion", 2, "Tablespoon");
        addIngredient(guacamole, "serrano chiles, stems and seeds removed, minced", 2, "Piece");
        addIngredient(guacamole, "cilantro (leaves and tender stems), finely chopped", 2, "Tablespoon");
        addIngredient(guacamole, "ripe tomato, seeds and pulp removed, chopped", 1f/2, "As needed");
        addIngredient(guacamole, "red radishes or jicama, to garnish", 1, "As needed");
        addIngredient(guacamole, "tortilla chips, to serve", 1, "As needed");

        guacamole.setDirections("1 Cut the avocado, remove flesh: Cut the avocados in half. Remove the pit. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl.\n" +
                "\n" +
                "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)\n" +
                "\n" +
                "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "\n" +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
                "\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                "\n" +
                "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.\n" +
                "\n" +
                "4 Serve: Serve immediately, or if making a few hours ahead, place plastic wrap on the surface of the guacamole and press down to cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.\n");
        Notes notes = new Notes();
        notes.setRecipeNotes("Once you have basic guacamole down, feel free to experiment with variations including strawberries, peaches, pineapple, mangoes, even watermelon. One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). You can get creative with homemade guacamole!");

        guacamole.setNotes(notes);

        return guacamole;
    }

    private void addIngredient(Recipe recipe, String ingredientName, float amount, String uomDescription) {
        UnitOfMeasure uom = getUnitOfMeasure(uomDescription);
        Ingredient ingredient = new Ingredient(ingredientName, new BigDecimal(amount), uom);
        recipe.addIngredient(ingredient);
    }

    private UnitOfMeasure getUnitOfMeasure(String description) {
        return unitOfMeasureRepository
                .findByDescription(description)
                .orElseThrow(() -> new RuntimeException("Unit of measure '" + description + "' not found"));
    }
}
