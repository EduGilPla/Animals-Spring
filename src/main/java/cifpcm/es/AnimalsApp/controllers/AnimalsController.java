package cifpcm.es.AnimalsApp.controllers;


import cifpcm.es.AnimalsApp.models.Animal;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import cifpcm.es.AnimalsApp.services.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AnimalsController {
    @Autowired
    private AnimalService service;
    public AnimalsController(){
        service = new AnimalService();
    }
    @GetMapping("/")
    public String listar(Model ViewData){
        ViewData.addAttribute("animalList",service.getAnimalList());
        return "index";
    }
    @GetMapping("/create")
    public String create(Model ViewData){
        Animal newAnimal = new Animal();
        ViewData.addAttribute("newAnimal", newAnimal);
        return "create";
    }
    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("newAnimal") Animal newAnimal, BindingResult bindingResult, Model Viewdata){
        if(bindingResult.hasErrors()){
            return "create";
        }
        service.addAnimal(newAnimal);
        return "redirect:/";
    }
}
