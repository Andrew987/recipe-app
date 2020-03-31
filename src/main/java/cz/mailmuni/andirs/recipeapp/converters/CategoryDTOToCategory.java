package cz.mailmuni.andirs.recipeapp.converters;

import cz.mailmuni.andirs.recipeapp.dto.CategoryDTO;
import cz.mailmuni.andirs.recipeapp.model.Category;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategoryDTOToCategory implements Converter<CategoryDTO, Category> {

    @Synchronized
    @Override
    public Category convert(CategoryDTO source) {
        if (source == null) {
            return null;
        }

        Category category = new Category();
        category.setId(source.getId());
        category.setDescription(source.getDescription());
        return category;
    }
}
