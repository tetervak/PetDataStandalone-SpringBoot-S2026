package ca.tetervak.petdata.repository;

import ca.tetervak.petdata.model.PetOwner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetOwnerRepository extends JpaRepository<PetOwner, Integer> {
}
