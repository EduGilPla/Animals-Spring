package cifpcm.es.AnimalsApp.services;

import cifpcm.es.AnimalsApp.models.Animal;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnimalService {
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
        Animal existingAnimal = findAnimal(toUpdate.getId());
        if (existingAnimal != null){
            int indexOf = animalList.indexOf(existingAnimal);
            animalList.set(indexOf,toUpdate);
            return UPDATED_CORRECTLY;
        }
        return ANIMAL_NOT_FOUND;
    }
    public Animal findAnimal(int id){
        return animalList.stream()
                .filter(animal -> animal.getId() == id)
                .findAny()
                .orElse(null);
    }
}
