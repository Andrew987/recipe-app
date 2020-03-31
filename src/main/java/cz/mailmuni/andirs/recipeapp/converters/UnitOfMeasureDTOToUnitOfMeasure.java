package cz.mailmuni.andirs.recipeapp.converters;

import cz.mailmuni.andirs.recipeapp.dto.UnitOfMeasureDTO;
import cz.mailmuni.andirs.recipeapp.model.UnitOfMeasure;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMeasureDTOToUnitOfMeasure implements Converter<UnitOfMeasureDTO, UnitOfMeasure> {

    @Synchronized
    @Override
    public UnitOfMeasure convert(UnitOfMeasureDTO source) {
        if (source == null) {
            return null;
        }

        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(source.getId());
        uom.setDescription(source.getDescription());
        return uom;
    }
}
