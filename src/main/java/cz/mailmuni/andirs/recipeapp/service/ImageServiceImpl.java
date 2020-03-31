package cz.mailmuni.andirs.recipeapp.service;

import cz.mailmuni.andirs.recipeapp.model.Recipe;
import cz.mailmuni.andirs.recipeapp.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

    private final RecipeRepository recipeRepository;

    public ImageServiceImpl( RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    @Transactional
    public void saveImageFile(Long recipeId, MultipartFile file) {

        try {
            Recipe recipe = recipeRepository.findById(recipeId).get();
            recipe.setImage(convertToByteArray(file));
            recipeRepository.save(recipe);
        } catch (IOException e) {
            //todo handle better
            log.error("Error occurred", e);
            e.printStackTrace();
        }
    }

    private Byte[] convertToByteArray(MultipartFile file) throws IOException {
        Byte[] byteObjects = new Byte[file.getBytes().length];

        int i = 0;

        for (byte b : file.getBytes()){
            byteObjects[i++] = b;
        }

        return byteObjects;
    }
}
