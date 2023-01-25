package cifpcm.es.AnimalsApp.services;

import cifpcm.es.AnimalsApp.interfaces.GroupService;
import cifpcm.es.AnimalsApp.models.AnimalGroup;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Primary
@Service
public class GroupServiceDB implements GroupService {
    final boolean OPERATION_SUCCESS = true;
    final boolean OPERATION_FAILED = false;
    private List<AnimalGroup> groups;

    @Override
    public List<AnimalGroup> getGroupList(){return groups;}

    @Override
    public boolean addGroup(AnimalGroup newGroup) {
        return false;
    }

    @Override
    public boolean deleteGroup(Optional<AnimalGroup> groupToDelete) {
        return false;
    }

    @Override
    public boolean updateGroup(AnimalGroup groupToUpdate) {
        return false;
    }

    @Override
    public Optional<AnimalGroup> findGroup(int id) {
        return Optional.empty();
    }

}
