package com.abi.swaggerapplication.modelTests;


import com.abi.swaggerapplication.model.Category;
import com.abi.swaggerapplication.model.Pet;
import com.abi.swaggerapplication.model.Tag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ModelTests {

    private static final String PET1_NAME = "Fido";

    private static final String STATUS_AVAILABLE = "Available";

    private static final String CATEGORY_DOG = "Dog";

    private static final String TAG_CUTE = "Cute";

    private static final String TAG_FRIENDLY = "Friendly";

    private static final String PHOTO_URL = "https://example.com/pet.jpg";


    @Test
    public void testPetProperties(){
        Pet pet = new Pet();
        pet.setId(1);
        pet.setName(PET1_NAME);
        pet.setStatus(STATUS_AVAILABLE);

        Category category = new Category();
        category.setId(123);
        category.setName(CATEGORY_DOG);
        pet.setCategory(category);

        List<String> photoUrls = new ArrayList<>();
        photoUrls.add(PHOTO_URL);
        pet.setPhotoUrls(photoUrls);

        List<Tag> tags = new ArrayList<>();
        Tag tag1 = new Tag();
        tag1.setId(101);
        tag1.setName(TAG_FRIENDLY);
        tags.add(tag1);

        Tag tag2 = new Tag();
        tag2.setId(102);
        tag2.setName(TAG_CUTE);
        tags.add(tag2);

        pet.setTags(tags);


        assertEquals(1, pet.getId());
        assertEquals(PET1_NAME, pet.getName());
        assertEquals(STATUS_AVAILABLE, pet.getStatus());

        Category petCategory = pet.getCategory();
        assertEquals(123, petCategory.getId());
        assertEquals(CATEGORY_DOG, petCategory.getName());

        List<String> petPhotoUrls = pet.getPhotoUrls();
        assertEquals(1, petPhotoUrls.size());
        assertEquals(PHOTO_URL, petPhotoUrls.get(0));

        List<Tag> petTags = pet.getTags();
        assertEquals(2, petTags.size());
        assertEquals(101, petTags.get(0).getId());
        assertEquals(TAG_FRIENDLY, petTags.get(0).getName());
        assertEquals(102, petTags.get(1).getId());
        assertEquals(TAG_CUTE, petTags.get(1).getName());
    }

}
