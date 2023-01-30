package cifpcm.es.AnimalsApp.controllers;

import cifpcm.es.AnimalsApp.interfaces.GroupService;
import cifpcm.es.AnimalsApp.models.AnimalGroup;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class GroupsController {
    @Autowired
    private GroupService groupService;

    @GetMapping("/groups")
    public String List(Model ViewData){
        ViewData.addAttribute("groupList",groupService.getGroupList());
        return "/groups/index";
    }
    @GetMapping("/groups/create")
    public String Create(Model ViewData){
        AnimalGroup newGroup = new AnimalGroup();
        ViewData.addAttribute("newGroup",newGroup);
        return "/groups/create";
    }
    @PostMapping("/groups/create")
    public String Create(@Valid @ModelAttribute("newGroup") AnimalGroup newGroup,BindingResult bindingResult, Model ViewData){
        if(bindingResult.hasErrors())
            return "groups/create";
        if(groupService.addGroup(newGroup))
            return "redirect:/groups";
        ViewData.addAttribute("error","No se ha podido crear el animal, fallo de servidor");
        return "/groups/index";
    }
    @GetMapping("/groups/details/{id}")
    public String Details(@PathVariable String id, Model ViewData){

        Optional<AnimalGroup> foundGroup = groupService.findGroup(Integer.parseInt(id));

        if(foundGroup.isEmpty()){
            ViewData.addAttribute("error","El grupo con id " + id + " no existe");
            ViewData.addAttribute("groupList",groupService.getGroupList());
            return "/groups/index";
        }
        ViewData.addAttribute("group",foundGroup.get());
        return "/groups/details";
    }
    @GetMapping("/groups/delete/{id}")
    public String Delete(@PathVariable String id, Model ViewData){

        Optional<AnimalGroup> groupToDelete = groupService.findGroup(Integer.parseInt(id));

        if(groupToDelete.isEmpty()){
            ViewData.addAttribute("error","El grupo con id " + id + " no existe");
            ViewData.addAttribute("groupList",groupService.getGroupList());
            return "/groups/index";
        }
        ViewData.addAttribute("group",groupToDelete.get());
        return "/groups/delete";
    }
    @PostMapping("/groups/delete/{id}")
    public String Delete(@ModelAttribute("group") AnimalGroup groupToDelete, Model ViewData){
        if(!groupService.deleteGroup(groupToDelete.getId())){
            ViewData.addAttribute("error","No se ha podido eliminar el grupo");
            ViewData.addAttribute("groupList",groupService.getGroupList());
            return "/groups/index";
        }
        return "redirect:/groups";
    }
    @GetMapping("/groups/update/{id}")
    public String Update(@PathVariable String id, Model ViewData){
        Optional<AnimalGroup> groupToUpdate = groupService.findGroup(Integer.parseInt(id));
        if (groupToUpdate.isEmpty()){
            ViewData.addAttribute("error","El grupo con id " + id + " no existe");
            ViewData.addAttribute("groupList",groupService.getGroupList());
            return "/groups/index";
        }
        ViewData.addAttribute("groupToUpdate",groupToUpdate.get());
        return "/groups/update";
    }
    @PostMapping("/groups/update/{id}")
    public String Update(@Valid @ModelAttribute("groupToUpdate") AnimalGroup modifiedGroup, BindingResult bindingResult, Model ViewData){
        if(bindingResult.hasErrors()){
            ViewData.addAttribute("groupToUpdate",modifiedGroup);
            return "/groups/update";
        }
        if(!groupService.updateGroup(modifiedGroup)){
            ViewData.addAttribute("error","No se ha podido modificar el grupo por un fallo del servidor");
            ViewData.addAttribute("groupList",groupService.getGroupList());
            return "/groups/index";
        }
        return "redirect:/groups";
}
}
