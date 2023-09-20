package com.abi.swaggerapplication.controller;


import com.abi.swaggerapplication.model.Pet;
import com.abi.swaggerapplication.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pet")
public class PetController {

private final PetService petService;

    @Autowired
    public PetController(PetService petService) {
        this.petService = petService;
    }

    @GetMapping("/getAll")
    public List<Pet> getPets(){
        return petService.getPets();
    }

    @PostMapping
    public void addNewPet(@RequestBody Pet pet){
        petService.addPet(pet);
    }

    @GetMapping("/getPetStatus")
    public String getPetStatus() {
        return petService.getPetStatus();
    }

}
