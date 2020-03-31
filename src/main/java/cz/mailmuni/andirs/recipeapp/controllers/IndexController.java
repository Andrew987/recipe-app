package cz.mailmuni.andirs.recipeapp.controllers;

import cz.mailmuni.andirs.recipeapp.model.Recipe;
import cz.mailmuni.andirs.recipeapp.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class IndexController {

    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "index"})
    public String getIndexPage(Model model) {
        Iterable<Recipe> recipes = recipeService.findAllRecipes();
        model.addAttribute("recipes", recipes);
        return "index";
    }
}
