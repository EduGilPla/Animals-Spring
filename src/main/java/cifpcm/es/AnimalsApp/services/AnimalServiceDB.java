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
    final boolean OPERATION_SUCCESS = true;
    final boolean OPERATION_FAILED = false;
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
            return OPERATION_SUCCESS;
        }
        catch (Exception exception){
            return OPERATION_FAILED;
        }
    }
    @Override
    public boolean deleteAnimal(int id) {
        try {
            repository.deleteById(id);
            return OPERATION_SUCCESS;
        }
        catch (Exception exception){
            return OPERATION_FAILED;
        }
    }
    @Override
    public boolean updateAnimal(Animal toUpdate) {
        try {
            repository.save(toUpdate);
            return OPERATION_SUCCESS;
        }
        catch (Exception exception){
            return OPERATION_FAILED;
        }
    }
    @Override
    public Optional<Animal> findAnimal(int id) {
        return repository.findById(id);
    }
}
