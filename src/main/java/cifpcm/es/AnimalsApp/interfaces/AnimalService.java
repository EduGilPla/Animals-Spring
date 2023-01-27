package cifpcm.es.AnimalsApp.interfaces;

import cifpcm.es.AnimalsApp.models.Animal;

import java.util.List;
import java.util.Optional;

public interface AnimalService {
    List<Animal> getAnimalList();
    boolean addAnimal(Animal newAnimal);
    boolean deleteAnimal(int id);
    boolean updateAnimal(Animal toUpdate);
    Optional<Animal> findAnimal(int id);
}
