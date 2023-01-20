package cifpcm.es.AnimalsApp.services;

import cifpcm.es.AnimalsApp.models.Animal;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnimalService {
    private static int currentId = 1;
    private static List<Animal> animalList = new ArrayList<>() {
        {
            add(new Animal(currentId++,"Pandora", "Gato", 20, false));
            add(new Animal(currentId++,"Vilma", "Perro", 15, false));
            add(new Animal(currentId++,"Manitas", "T-Rex", 28, true));
        }
    };
    public List<Animal> getAnimalList() {
        return animalList;
    }
    public void addAnimal(Animal newAnimal){
        newAnimal.setId(currentId++);
        this.animalList.add(newAnimal);
    }
    public boolean deleteAnimal(int id){
        return true;
    }
    public boolean modifyAnimal(){
        return true;
    }
}
