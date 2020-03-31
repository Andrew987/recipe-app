package cz.mailmuni.andirs.recipeapp.controllers;


import cz.mailmuni.andirs.recipeapp.dto.RecipeDTO;
import cz.mailmuni.andirs.recipeapp.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/recipe")
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("show/{id}")
    public String showById(@PathVariable Long id,  Model model) {
        model.addAttribute("recipe", recipeService.findById(id));
        return "recipe/show";
    }

    @GetMapping("new")
    public String newRecipe(Model model) {
        model.addAttribute("recipe", new RecipeDTO());
        return "recipe/recipeform";
    }

    @GetMapping("update/{id}")
    public String updateRecipe(@PathVariable Long id, Model model) {
        model.addAttribute("recipe", recipeService.findById(id));
        return "recipe/recipeform";
    }

    @PostMapping
    @RequestMapping("save")
    public String saveOrUpdate(@ModelAttribute RecipeDTO recipeDTO) {
        RecipeDTO savedDTO = recipeService.saveRecipeDTO(recipeDTO);

        return "redirect:/recipe/show/" + savedDTO.getId();
    }

    @GetMapping("delete/{id}")
    public String deleteRecipe(@PathVariable Long id, Model model) {
        log.debug("Deleting id: " + id);

        recipeService.deleteById(id);
        return "redirect:/";
    }
}
