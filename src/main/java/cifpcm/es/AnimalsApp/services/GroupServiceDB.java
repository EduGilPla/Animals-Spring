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
    GroupRepository groupRepository;
    @Override
    public List<AnimalGroup> getGroupList(){return groupRepository.findAll();}
    @Override
    public boolean addGroup(AnimalGroup newGroup) {
        try {
            groupRepository.save(newGroup);
            return OPERATION_SUCCESS;
        }
        catch (Exception exception){
            return OPERATION_FAILED;
        }
    }
    @Override
    public boolean deleteGroup(int id) {
        try {
            groupRepository.deleteById(id);
            return OPERATION_SUCCESS;
        }
        catch (Exception exception){
            return OPERATION_FAILED;
        }
    }
    @Override
    public boolean updateGroup(AnimalGroup groupToUpdate) {
        try {
            Optional<AnimalGroup> groupQuery = groupRepository.findById(groupToUpdate.getId());
            if(groupQuery.isPresent()){
                AnimalGroup updatedGroup = groupQuery.get();
                updatedGroup.setName(groupToUpdate.getName());
                groupRepository.save(updatedGroup);
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
        return groupRepository.findById(id);
    }

}
