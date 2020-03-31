package cz.mailmuni.andirs.recipeapp.service;

import cz.mailmuni.andirs.recipeapp.dto.UnitOfMeasureDTO;
import cz.mailmuni.andirs.recipeapp.model.UnitOfMeasure;

import java.util.Set;

public interface UnitOfMeasureService {
    Set<UnitOfMeasure> findAll();
    Set<UnitOfMeasureDTO> findAllDTOs();
}
