package ca.tetervak.petdata.data;

import ca.tetervak.petdata.model.Pet;
import ca.tetervak.petdata.model.PetOwner;
import ca.tetervak.petdata.repository.PetOwnerRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInitializer {

    private final PetOwnerRepository petOwnerRepository;

    public DatabaseInitializer(PetOwnerRepository petOwnerRepository) {
        this.petOwnerRepository = petOwnerRepository;
    }

    @PostConstruct
    public void init(){

        long ownerCount = petOwnerRepository.count();
        if(ownerCount > 0){
            return;
        }

        PetOwner patricia = new PetOwner("Patricia", "Johnson");
        patricia.addPet(new Pet("Charlie", "dog", 3, "charlie_dog.jpg"));
        patricia.addPet(new Pet("Fluffy", "cat", 2, "fluffy_cat.jpg"));
        petOwnerRepository.save(patricia);

        PetOwner lisa = new PetOwner("Lisa", "Anderson");
        lisa.addPet(new Pet("Oscar", "cat", 6,"oscar_cat.jpg"));
        petOwnerRepository.save(lisa);

        PetOwner kimberly = new PetOwner("Kimberly", "Green");
        petOwnerRepository.save(kimberly);

        PetOwner brenda = new PetOwner("Brenda", "Clark");
        brenda.addPet(new Pet("Tweety", "canary", 3, "tweety_canary.jpg"));
        brenda.addPet(new Pet("Riley", "dog", 5, "riley_dog.jpg"));
        brenda.addPet(new Pet("Sam", "rabbit", 2, "sam_rabbit.jpg"));
        brenda.addPet(new Pet("Marx", "hamster", 1, "marx_hamster.jpg"));

        petOwnerRepository.save(brenda);

        petOwnerRepository.flush();
    }
}
