package com.abi.cucumbertests.petRepo;




import com.abi.cucumbertests.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepo extends JpaRepository<Pet, Integer> {
}
