package com.abi.swaggerapplication.controllerTests;


import com.abi.swaggerapplication.controller.PetController;
import com.abi.swaggerapplication.model.Pet;
import com.abi.swaggerapplication.service.PetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ControllerTests {

    private static final String PET1_NAME = "Fido";
    private static final String PET2_NAME = "Buddy";
    private static final String STATUS_AVAILABLE = "Available";

    private PetController petController;
    private PetService petService;

    @BeforeEach
    public void setUp(){
        petService = mock(PetService.class);
        petController = new PetController(petService);
    }

    @Test
    public void testGetPets() {

        List<Pet> pets = new ArrayList<>();
        Pet pet1 = new Pet();
        pet1.setId(1);
        pet1.setName(PET1_NAME);
        pet1.setStatus(STATUS_AVAILABLE);

        Pet pet2 = new Pet();
        pet2.setId(2);
        pet2.setName(PET2_NAME);
        pet2.setStatus(STATUS_AVAILABLE);

        pets.add(pet1);
        pets.add(pet2);


        when(petService.getPets()).thenReturn(pets);


        List<Pet> result = petController.getPets();

        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getId());
        assertEquals(2, result.get(1).getId());
    }

    @Test
    public void testAddNewPet() {
        List<Pet> pets = new ArrayList<>();
        Pet pet = new Pet();
        pet.setId(1);
        pet.setName(PET1_NAME);
        pet.setStatus(STATUS_AVAILABLE);
        pets.add(pet);
        petController.addNewPet(pet);

        when(petService.getPets()).thenReturn(pets);
        List<Pet> result = petController.getPets();

        assertTrue(result.contains(pet));
    }


    @Test
    public void testGetPetStatus() {

        String status = "available";


        when(petService.getPetStatus()).thenReturn(status);


        String result = petController.getPetStatus();


        assertEquals(status, result);

}


}
