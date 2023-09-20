package com.abi.swaggerapplication.serviceTests;


import com.abi.swaggerapplication.model.Category;
import com.abi.swaggerapplication.model.Pet;
import com.abi.swaggerapplication.model.Tag;
import com.abi.swaggerapplication.repository.CategoryRepo;
import com.abi.swaggerapplication.repository.PetRepo;
import com.abi.swaggerapplication.repository.TagRepo;
import com.abi.swaggerapplication.service.PetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class PetServiceTest {
    private static final String PET1_NAME = "fluffy";

    private static final String PET2_NAME = "Fido";

    private static final String STATUS_AVAILABLE = "Available";

    private static final String CATEGORY_DOG = "Dog";

    private static final String TAG_CUTE = "Cute";

    private static final String TAG_FRIENDLY = "Friendly";

    private static final String PHOTO_URL = "https://example.com/pet.jpg";
    @InjectMocks
    private PetService petService;

    @Mock
    private PetRepo petRepo;

    @Mock
    private TagRepo tagRepo;

    @Mock
    private CategoryRepo categoryRepo;



    @BeforeEach
    public void setUp() {
        petService = new PetService(petRepo,tagRepo,categoryRepo);
    }


    @Test
    public void testGetPets() {

        petService.getPets();

        verify(petRepo).findAll();
    }

    @Test
    @Disabled
    public void testAddPet() {
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
        petService.addPet(pet);

        verify(petRepo).save(pet);
        verify(tagRepo).saveAll(tags);
        verify(categoryRepo).save(category);


    }

    @Test
    @Disabled
    public void testGetPetStatus() {

        String actualStatus = petService.getPetStatus();
        assertTrue(
                actualStatus.equals("available") ||
                        actualStatus.equals("sold") ||
                        actualStatus.equals("pending")

        );
    }

}
