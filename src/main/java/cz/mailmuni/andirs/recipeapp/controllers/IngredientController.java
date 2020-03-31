package cz.mailmuni.andirs.recipeapp.controllers;

import cz.mailmuni.andirs.recipeapp.dto.IngredientDTO;
import cz.mailmuni.andirs.recipeapp.dto.UnitOfMeasureDTO;
import cz.mailmuni.andirs.recipeapp.service.IngredientService;
import cz.mailmuni.andirs.recipeapp.service.RecipeService;
import cz.mailmuni.andirs.recipeapp.service.UnitOfMeasureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class IngredientController {
    private final IngredientService ingredientService;
    private final RecipeService recipeService;
    private final UnitOfMeasureService unitOfMeasureService;

    public IngredientController(IngredientService ingredientService, RecipeService recipeService, UnitOfMeasureService unitOfMeasureService) {
        this.ingredientService = ingredientService;
        this.recipeService = recipeService;
        this.unitOfMeasureService = unitOfMeasureService;
    }

    @GetMapping("/recipe/{recipeId}/ingredients")
    public String listIngredients(@PathVariable Long recipeId, Model model){
        log.debug("Getting ingredient list for recipe id: " + recipeId);

        model.addAttribute("recipe", recipeService.findDTOById(recipeId));
        return "recipe/ingredient/list";
    }

    @GetMapping("recipe/{recipeId}/ingredient/{id}/show")
    public String showRecipeIngredient(@PathVariable Long recipeId,
                                       @PathVariable Long id, Model model){
        model.addAttribute("ingredient", ingredientService.findById(id));
        return "recipe/ingredient/show";
    }

    @GetMapping("recipe/{recipeId}/ingredient/new")
    public String newRecipe(@PathVariable Long recipeId, Model model){

        if (!recipeService.exists(recipeId)) {
            throw new RuntimeException("Recipe with ID " + recipeId + " does not exist");
        }

        IngredientDTO ingredientDTO = new IngredientDTO();
        ingredientDTO.setRecipeId(recipeId);
        model.addAttribute("ingredient", ingredientDTO);

        //init uom
        ingredientDTO.setUom(new UnitOfMeasureDTO());

        model.addAttribute("uomList",  unitOfMeasureService.findAllDTOs());

        return "recipe/ingredient/ingredientform";
    }

    @GetMapping("recipe/{recipeId}/ingredient/{id}/update")
    public String updateRecipeIngredient(@PathVariable Long recipeId,
                                         @PathVariable Long id, Model model){
        model.addAttribute("ingredient", ingredientService.findDTOById(id));
        model.addAttribute("uomList", unitOfMeasureService.findAllDTOs());
        return "recipe/ingredient/ingredientform";
    }

    @PostMapping("recipe/{recipeId}/ingredient")
    public String saveOrUpdate(@ModelAttribute IngredientDTO dto){
        IngredientDTO savedDto = ingredientService.saveIngredientDTO(dto);

        log.debug("saved ingredient id:" + savedDto.getId());

        return "redirect:/recipe/" + savedDto.getRecipeId() + "/ingredient/" + savedDto.getId() + "/show";
    }

    @GetMapping("recipe/{recipeId}/ingredient/{id}/delete")
    public String deleteIngredient(@PathVariable Long recipeId,
                                   @PathVariable Long id){

        log.debug("deleting ingredient id:" + id);
        ingredientService.deleteById(id);

        return "redirect:/recipe/" + recipeId + "/ingredients";
    }
}
