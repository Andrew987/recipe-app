package cz.mailmuni.andirs.recipeapp.converters;

import cz.mailmuni.andirs.recipeapp.dto.NotesDTO;
import cz.mailmuni.andirs.recipeapp.model.Notes;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class NotesToNotesDTO implements Converter<Notes, NotesDTO> {

    @Synchronized
    @Override
    public NotesDTO convert(Notes source) {
        if (source == null) {
            return null;
        }

        NotesDTO notes = new NotesDTO();
        notes.setId(source.getId());
        notes.setRecipeNotes(source.getRecipeNotes());
        return notes;
    }
}
