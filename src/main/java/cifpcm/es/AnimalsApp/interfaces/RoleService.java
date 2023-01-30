package cifpcm.es.AnimalsApp.interfaces;

import cifpcm.es.AnimalsApp.models.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {
  List<Role> getRoleList();

  Optional<Role> findRoleByName(String name);
}
