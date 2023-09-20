package com.abi.swaggerapplication.configuration;


import com.abi.swaggerapplication.model.Category;
import com.abi.swaggerapplication.model.Pet;
import com.abi.swaggerapplication.model.Tag;
import com.abi.swaggerapplication.repository.CategoryRepo;
import com.abi.swaggerapplication.repository.PetRepo;
import com.abi.swaggerapplication.repository.TagRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class PetConfig {

    @Bean
    CommandLineRunner commandLineRunner(PetRepo petRepo, CategoryRepo categoryRepo, TagRepo tagRepo) {
        return args -> {
            Category dogCategory = new Category();
            dogCategory.setId(10);
            dogCategory.setName("Dog");
            categoryRepo.save(dogCategory);

            Tag petTag = new Tag();
            petTag.setId(10);
            petTag.setName("Viel Fell");
            tagRepo.save(petTag);

            Pet dog = new Pet();
            dog.setId(10);
            dog.setName("Rocky");
            dog.setCategory(dogCategory);
            dog.setPhotoUrls(List.of("photo_url", "haha"));
            dog.setTags(List.of(petTag));
            dog.setStatus("sold");

            petRepo.save(dog);
        };
    }

}
