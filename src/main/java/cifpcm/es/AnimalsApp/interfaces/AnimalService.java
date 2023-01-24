package cifpcm.es.AnimalsApp.interfaces;

import cifpcm.es.AnimalsApp.models.Animal;

import java.util.List;
import java.util.Optional;

public interface AnimalService {
    public List<Animal> getAnimalList();
    public boolean addAnimal(Animal newAnimal);
    public boolean deleteAnimal(Optional<Animal> toDelete);
    public boolean updateAnimal(Animal toUpdate);
    public Optional<Animal> findAnimal(int id);
}
