package cifpcm.es.AnimalsApp.repositories;

import cifpcm.es.AnimalsApp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findByEmail(String Username);
}
