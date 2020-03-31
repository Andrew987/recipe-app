package cz.mailmuni.andirs.recipeapp.converters;

import cz.mailmuni.andirs.recipeapp.dto.UnitOfMeasureDTO;
import cz.mailmuni.andirs.recipeapp.model.UnitOfMeasure;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class UnitOfMeasureToUnitOfMeasureDTO implements Converter<UnitOfMeasure, UnitOfMeasureDTO> {

    @Synchronized
    @Override
    public UnitOfMeasureDTO convert(UnitOfMeasure source) {
        if (source == null) {
            return null;
        }

        UnitOfMeasureDTO uom = new UnitOfMeasureDTO();
        uom.setId(source.getId());
        uom.setDescription(source.getDescription());
        return uom;
    }

    public Set<UnitOfMeasureDTO> convert(Set<UnitOfMeasure> source) {
        Set<UnitOfMeasureDTO> uomDtos = new HashSet<>();
        source.forEach(uom -> uomDtos.add(convert(uom)));
        return uomDtos;
    }
}
