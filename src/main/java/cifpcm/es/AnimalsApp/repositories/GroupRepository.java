package cifpcm.es.AnimalsApp.repositories;

import cifpcm.es.AnimalsApp.models.AnimalGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<AnimalGroup,Integer> {
}
