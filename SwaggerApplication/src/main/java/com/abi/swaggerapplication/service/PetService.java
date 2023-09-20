package com.abi.swaggerapplication.service;


import com.abi.swaggerapplication.model.Pet;
import com.abi.swaggerapplication.repository.CategoryRepo;
import com.abi.swaggerapplication.repository.PetRepo;
import com.abi.swaggerapplication.repository.TagRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
public class PetService {

    @Value("${myapp.wiremock}")
    private String wireMockBaseUrl;
    private final PetRepo petRepo;
    private final TagRepo tagRepo;
    private final CategoryRepo categoryRepo;

    @Autowired
    public PetService(PetRepo petRepo, TagRepo tagRepo, CategoryRepo categoryRepo) {
        this.petRepo = petRepo;
        this.tagRepo = tagRepo;
        this.categoryRepo = categoryRepo;
    }


    public List<Pet> getPets() {
        return petRepo.findAll();
    }

    public void addPet(Pet pet) {

        String status = getPetStatus();

        pet.setStatus(status);

        categoryRepo.save(pet.getCategory());
        tagRepo.saveAll(pet.getTags());
        petRepo.save(pet);


    }


    public String getPetStatus() {
        String url = wireMockBaseUrl + "/api/getPetStatus";

        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.getForObject(url, String.class);
    }
}
