package cifpcm.es.AnimalsApp.repositories;

import cifpcm.es.AnimalsApp.models.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository extends JpaRepository<Animal,Integer> {
}
