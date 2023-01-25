package cifpcm.es.AnimalsApp.controllers;


import cifpcm.es.AnimalsApp.interfaces.AnimalService;
import cifpcm.es.AnimalsApp.interfaces.GroupService;
import cifpcm.es.AnimalsApp.models.Animal;
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
}
