package cifpcm.es.AnimalsApp.services;

import cifpcm.es.AnimalsApp.interfaces.GroupService;
import cifpcm.es.AnimalsApp.models.AnimalGroup;
import cifpcm.es.AnimalsApp.repositories.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Primary
@Service
public class GroupServiceDB implements GroupService {
    final boolean OPERATION_SUCCESS = true;
    final boolean OPERATION_FAILED = false;
    @Autowired
    GroupRepository repository;

    @Override
    public List<AnimalGroup> getGroupList(){return repository.findAll();}
    @Override
    public boolean addGroup(AnimalGroup newGroup) {
        try {
            repository.save(newGroup);
            return OPERATION_SUCCESS;
        }
        catch (Exception exception){
            return OPERATION_FAILED;
        }
    }
    @Override
    public boolean deleteGroup(AnimalGroup groupToDelete) {
        try {
            repository.delete(groupToDelete);
            return OPERATION_SUCCESS;
        }
        catch (Exception exception){
            return OPERATION_FAILED;
        }
    }

    @Override
    public boolean updateGroup(AnimalGroup groupToUpdate) {
        try {
            Optional<AnimalGroup> groupToChange = repository.findById(groupToUpdate.getId());
            if(groupToChange.isPresent()){
                AnimalGroup updatedGroup = groupToChange.get();
                updatedGroup.setName(groupToUpdate.getName());
                repository.save(updatedGroup);
                return OPERATION_SUCCESS;
            }
            return OPERATION_FAILED;
        }
        catch (Exception exception){
            return OPERATION_FAILED;
        }
    }
    @Override
    public Optional<AnimalGroup> findGroup(int id) {
        return repository.findById(id);
    }

}
