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
    public void addAnimal(Animal newAnimal) throws Exception{
        try {
            newAnimal.setId(currentId++);
            this.animalList.add(newAnimal);
        }
        catch (Exception exception){
            throw exception;
        }
    }
    public boolean deleteAnimal(Animal toDelete){
        try {
            animalList.remove(toDelete);
            return true;
        }
        catch (Exception exception){
            return false;
        }
    }
    public boolean updateAnimal(Animal toUpdate){
        Animal existingAnimal = findAnimal(toUpdate.getId());
        if (existingAnimal == null)
            return false;
        int indexOf = animalList.indexOf(existingAnimal);
        animalList.set(indexOf,toUpdate);
        return true;
    }
    public Animal findAnimal(int id){
        return animalList.stream()
                .filter(animal -> animal.getId() == id)
                .findAny()
                .orElse(null);
    }
}
