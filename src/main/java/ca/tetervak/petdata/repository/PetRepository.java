package ca.tetervak.petdata.repository;

import ca.tetervak.petdata.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Integer> {
}
