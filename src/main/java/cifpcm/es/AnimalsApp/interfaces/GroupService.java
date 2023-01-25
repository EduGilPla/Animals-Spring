package cifpcm.es.AnimalsApp.interfaces;

import cifpcm.es.AnimalsApp.models.AnimalGroup;

import java.util.List;
import java.util.Optional;

public interface GroupService {
    List<AnimalGroup> getGroupList();
    boolean addGroup(AnimalGroup newGroup);
    boolean deleteGroup(AnimalGroup groupToDelete);
    boolean updateGroup(AnimalGroup groupToUpdate);
    Optional<AnimalGroup> findGroup(int id);
}
