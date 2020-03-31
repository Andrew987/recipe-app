package cz.mailmuni.andirs.recipeapp.converters;

import cz.mailmuni.andirs.recipeapp.dto.NotesDTO;
import cz.mailmuni.andirs.recipeapp.model.Notes;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class NotesDTOToNotes implements Converter<NotesDTO, Notes> {

    @Synchronized
    @Override
    public Notes convert(NotesDTO source) {
        if (source == null) {
            return null;
        }

        Notes notes = new Notes();
        notes.setId(source.getId());
        notes.setRecipeNotes(source.getRecipeNotes());
        return notes;
    }
}
