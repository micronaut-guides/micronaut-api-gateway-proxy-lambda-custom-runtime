package petstoregraal;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.QueryValue;

import javax.annotation.Nullable;
import java.security.Principal;
import java.util.Optional;
import java.util.UUID;

@Controller
public class PetsController {
    private final PetData petData;

    public PetsController(PetData petData) {
        this.petData = petData;
    }

    @Post("/pets")
    public Pet createPet(@Body Pet newPet) {
        if (newPet.getName() == null || newPet.getBreed() == null) {
            return null;
        }

        Pet dbPet = newPet;
        dbPet.setId(UUID.randomUUID().toString());
        return dbPet;
    }

    @Get("/pets")
    public Pet[] listPets(@QueryValue Optional<Integer> limit, @Nullable Principal principal) {
        int queryLimit = 10;
        if (limit.isPresent()) {
            queryLimit = limit.get();
        }

        Pet[] outputPets = new Pet[queryLimit];

        for (int i = 0; i < queryLimit; i++) {
            Pet newPet = new Pet();
            newPet.setId(UUID.randomUUID().toString());
            newPet.setName(petData.getRandomName());
            newPet.setBreed(petData.getRandomBreed());
            newPet.setDateOfBirth(petData.getRandomDoB());
            outputPets[i] = newPet;
        }

        return outputPets;
    }

    @Get("/pets/{petId}")
    public Pet getPet(@PathVariable String petId) {
        Pet newPet = new Pet();
        newPet.setId(UUID.randomUUID().toString());
        newPet.setBreed(petData.getRandomBreed());
        newPet.setDateOfBirth(petData.getRandomDoB());
        newPet.setName(petData.getRandomName());
        return newPet;
    }
}
