package cifpcm.es.AnimalsApp.services;

import cifpcm.es.AnimalsApp.interfaces.RoleService;
import cifpcm.es.AnimalsApp.models.Role;
import cifpcm.es.AnimalsApp.repositories.RoleRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Primary
@Service
public class RoleServiceDB implements RoleService {
  private RoleRepository roleRepository;
  @Override
  public List<Role> getRoleList() {
    return roleRepository.findAll();
  }

  @Override
  public Optional<Role> findRoleByName(String name) {
    return roleRepository.findByName(name);
  }
}
