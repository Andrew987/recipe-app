package cz.mailmuni.andirs.recipeapp.converters;

import cz.mailmuni.andirs.recipeapp.dto.CategoryDTO;
import cz.mailmuni.andirs.recipeapp.model.Category;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategoryToCategoryDTO implements Converter<Category, CategoryDTO> {

    @Synchronized
    @Override
    public CategoryDTO convert(Category source) {
        if (source == null) {
            return null;
        }

        CategoryDTO category = new CategoryDTO();
        category.setId(source.getId());
        category.setDescription(source.getDescription());
        return category;
    }
}
