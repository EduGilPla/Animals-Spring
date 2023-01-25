package cifpcm.es.AnimalsApp.controllers;


import cifpcm.es.AnimalsApp.interfaces.AnimalService;
import cifpcm.es.AnimalsApp.interfaces.GroupService;
import cifpcm.es.AnimalsApp.models.Animal;
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
    private GroupService service;

    @GetMapping("/groups")
    public String List(Model Viewdata){
        Viewdata.addAttribute("groupList",service.getGroupList());
        return "/groups/index";
    }
    @GetMapping("/groups/create")
    public String Create(Model ViewData){
        AnimalGroup newGroup = new AnimalGroup();
        ViewData.addAttribute("newGroup",newGroup);
        return "/groups/create";
    }
    @PostMapping("/groups/create")
    public String Create(@Valid @ModelAttribute("newGroup") AnimalGroup newGroup,BindingResult bindingResult, Model Viewdata){
        if(bindingResult.hasErrors())
            return "groups/create";
        if(service.addGroup(newGroup))
            return "redirect:/groups";
        Viewdata.addAttribute("error","No se ha podido crear el animal, fallo de servidor");
        return "/groups/index";
    }
}
