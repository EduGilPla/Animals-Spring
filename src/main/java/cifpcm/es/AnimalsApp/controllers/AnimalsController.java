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

import java.util.List;
import java.util.Optional;

@Controller
public class AnimalsController {
    @Autowired
    private AnimalService animalService;
    @Autowired
    private GroupService groupService;
    @GetMapping("/")
    public String Start(Model ViewData){
        ViewData.addAttribute("animalList",animalService.getAnimalList());
        return "/common/welcome";
    }
    @GetMapping("/animals")
    public String List(Model ViewData){
        ViewData.addAttribute("animalList",animalService.getAnimalList());
        return "/animals/index";
    }
    @GetMapping("/animals/create")
    public String Create(Model ViewData){
        Animal newAnimal = new Animal();
        ViewData.addAttribute("newAnimal", newAnimal);
        ViewData.addAttribute("groupList",groupService.getGroupList());
        return "/animals/create";
    }
    @PostMapping("/animals/create")
    public String Create(@Valid @ModelAttribute("newAnimal") Animal newAnimal, BindingResult bindingResult, Model Viewdata){
        if(bindingResult.hasErrors()){
            return "/animals/create";
        }
        if(animalService.addAnimal(newAnimal)){
            return "redirect:/animals";
        }
        Viewdata.addAttribute("error","No se ha podido crear el animal, fallo de servidor");
        return "/animals/index";
    }
    @GetMapping("/animals/details/{id}")
    public String Details(@PathVariable String id, Model ViewData){

        Optional<Animal> foundAnimal = animalService.findAnimal(Integer.parseInt(id));

        if (foundAnimal.isEmpty()){
            ViewData.addAttribute("error","El animal con id " + id + " no existe");
            ViewData.addAttribute("animalList",animalService.getAnimalList());
            return "/animals/index";
        }
        ViewData.addAttribute("foundAnimal" , foundAnimal.get());
        return "/animals/details";
    }
    @GetMapping("/animals/delete/{id}")
    public String Delete(@PathVariable String id, Model ViewData){

        Optional<Animal> animalToDelete = animalService.findAnimal(Integer.parseInt(id));

        if (animalToDelete.isEmpty()){
            ViewData.addAttribute("error","El animal con id " + id + " no existe");
            ViewData.addAttribute("animalList",animalService.getAnimalList());
            return "/animals/index";
        }
        if(!animalService.deleteAnimal(animalToDelete)){
            ViewData.addAttribute("error","No se ha podido borrar el elemento");
            ViewData.addAttribute("animalList",animalService.getAnimalList());
            return "/animals/index";
        }
        return "redirect:/";
    }
    @GetMapping("/animals/update/{id}")
    public String Update(@PathVariable String id, Model ViewData){
        Optional<Animal> animalToUpdate = animalService.findAnimal(Integer.parseInt(id));
        if (animalToUpdate.isEmpty()){
            ViewData.addAttribute("error","El animal con id " + id + " no existe");
            ViewData.addAttribute("animalList",animalService.getAnimalList());
            return "/animals/index";
        }
        ViewData.addAttribute("animalToUpdate",animalToUpdate.get());
        return "/animals/update";
    }
    @PostMapping("/animals/update/{id}")
    public String Update(@Valid @ModelAttribute("animalToUpdate") Animal modifiedAnimal, BindingResult bindingResult, Model Viewdata){
        try {
            if(bindingResult.hasErrors()){
                Viewdata.addAttribute("animalToUpdate",modifiedAnimal);
                return "/animals/update";
            }
            if(!animalService.updateAnimal(modifiedAnimal)){
                Viewdata.addAttribute("error","No se ha podido modificar el objeto por un fallo del servidor");
                return "redirect:/";
            }
            return "redirect:/";
        }
        catch(Exception exception) {
            Viewdata.addAttribute("exception",exception);
            return "/animals/index";
        }
    }
}
