package cifpcm.es.AnimalsApp.services;

import cifpcm.es.AnimalsApp.interfaces.AnimalService;
import cifpcm.es.AnimalsApp.models.Animal;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AnimalServiceLocal implements AnimalService {
    private static int currentId = 1;
    private List<Animal> animalList = new ArrayList<>() {
        {
            add(new Animal(currentId++,"Pandora", "Gato", 20, false));
            add(new Animal(currentId++,"Vilma", "Perro", 15, false));
            add(new Animal(currentId++,"Manitas", "T-Rex", 28, true));
        }
    };
    public List<Animal> getAnimalList() {
        return animalList;
    }
    public boolean addAnimal(Animal newAnimal){
        boolean CREATED_CORRECTLY = true;
        boolean OPERATION_FAILED = false;
        try {
            newAnimal.setId(currentId++);
            this.animalList.add(newAnimal);
            return CREATED_CORRECTLY;
        }
        catch (Exception exception){
            return OPERATION_FAILED;
        }
    }
    public boolean deleteAnimal(Animal toDelete){
        boolean COMPLETED_CORRECTLY = true;
        boolean OPERATION_FAILED = false;
        try {
            animalList.remove(toDelete);
            return COMPLETED_CORRECTLY;
        }
        catch (Exception exception){
            return OPERATION_FAILED;
        }
    }
    public boolean updateAnimal(Animal toUpdate){
        boolean UPDATED_CORRECTLY = true;
        boolean ANIMAL_NOT_FOUND = false;
        Optional<Animal> existingAnimal = findAnimal(toUpdate.getId());
        if (existingAnimal.isPresent()){
            int indexOf = animalList.indexOf(existingAnimal);
            animalList.set(indexOf,toUpdate);
            return UPDATED_CORRECTLY;
        }
        return ANIMAL_NOT_FOUND;
    }
    public Optional<Animal> findAnimal(int id){
        return Optional.ofNullable(animalList.stream()
                .filter(animal -> animal.getId() == id)
                .findAny()
                .orElse(null));
    }
}
