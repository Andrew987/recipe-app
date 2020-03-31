package cz.mailmuni.andirs.recipeapp.service;

import cz.mailmuni.andirs.recipeapp.converters.UnitOfMeasureToUnitOfMeasureDTO;
import cz.mailmuni.andirs.recipeapp.dto.UnitOfMeasureDTO;
import cz.mailmuni.andirs.recipeapp.model.UnitOfMeasure;
import cz.mailmuni.andirs.recipeapp.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {

    private final UnitOfMeasureRepository uomRepository;
    private final UnitOfMeasureToUnitOfMeasureDTO uomConverter;

    public UnitOfMeasureServiceImpl(UnitOfMeasureRepository uomRepository, UnitOfMeasureToUnitOfMeasureDTO uomConverter) {
        this.uomRepository = uomRepository;
        this.uomConverter = uomConverter;
    }

    @Override
    public Set<UnitOfMeasure> findAll() {
        Set<UnitOfMeasure> uoms = new HashSet<>();
        uomRepository.findAll().forEach(uom -> uoms.add(uom));
        return uoms;
    }

    @Override
    public Set<UnitOfMeasureDTO> findAllDTOs() {
        return uomConverter.convert(findAll());
    }
}
