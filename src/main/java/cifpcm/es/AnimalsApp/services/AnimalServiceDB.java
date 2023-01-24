package cifpcm.es.AnimalsApp.services;

import cifpcm.es.AnimalsApp.interfaces.AnimalService;
import cifpcm.es.AnimalsApp.models.Animal;
import cifpcm.es.AnimalsApp.repositories.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Primary
@Service
public class AnimalServiceDB implements AnimalService {
    @Autowired
    AnimalRepository repository;
    @Override
    public List<Animal> getAnimalList() {
        return repository.findAll();
    }

    @Override
    public boolean addAnimal(Animal newAnimal) {
        try {
            repository.save(newAnimal);
            return true;
        }
        catch (Exception exception){
            return false;
        }
    }
    @Override
    public boolean deleteAnimal(Optional<Animal> toDelete) {
        try {
            repository.delete(toDelete.get());
            return true;
        }
        catch (Exception exception){
            return false;
        }
    }
    @Override
    public boolean updateAnimal(Animal toUpdate) {
        try {
            repository.save(toUpdate);
            return true;
        }
        catch (Exception exception){
            return false;
        }
    }

    @Override
    public Optional<Animal> findAnimal(int id) {
        return repository.findById(id);
    }
}
