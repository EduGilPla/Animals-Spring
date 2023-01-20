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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AnimalsController {
    @Autowired
    private AnimalService service;
    @GetMapping("/")
    public String listar(Model ViewData){
        ViewData.addAttribute("animalList",service.getAnimalList());
        return "/animals/index";
    }
    @GetMapping("/animals/create")
    public String create(Model ViewData){
        Animal newAnimal = new Animal();
        ViewData.addAttribute("newAnimal", newAnimal);
        return "/animals/create";
    }
    @PostMapping("/animals/create")
    public String create(@Valid @ModelAttribute("newAnimal") Animal newAnimal, BindingResult bindingResult, Model Viewdata){
        try {
            if(bindingResult.hasErrors()){
                return "/animals/create";
            }
            service.addAnimal(newAnimal);
            return "redirect:/";
        }
        catch(Exception exception) {
            Viewdata.addAttribute("exception",exception);
            return "/animals/index";
        }
    }
    @GetMapping("/animals/details/{id}")
    public String details(@PathVariable String id, Model ViewData){

        Animal foundAnimal = service.findAnimal(Integer.parseInt(id));

        if (foundAnimal == null){
            ViewData.addAttribute("error","El animal con id " + id + " no existe");
            ViewData.addAttribute("animalList",service.getAnimalList());
            return "/animals/index";
        }
        ViewData.addAttribute("foundAnimal" , foundAnimal);
        return "/animals/details";
    }
    @GetMapping("/animals/delete/{id}")
    public String delete(@PathVariable String id, Model ViewData){

        Animal animalToDelete = service.findAnimal(Integer.parseInt(id));

        if (animalToDelete == null){
            ViewData.addAttribute("error","El animal con id " + id + " no existe");
            ViewData.addAttribute("animalList",service.getAnimalList());
            return "/animals/index";
        }
        if(!service.deleteAnimal(animalToDelete)){
            ViewData.addAttribute("error","No se ha podido borrar el elemento");
            ViewData.addAttribute("animalList",service.getAnimalList());
            return "/animals/index";
        }
        return "redirect:/";
    }
    @GetMapping("/animals/update/{id}")
    public String update(@PathVariable String id, Model ViewData){
        Animal animalToUpdate = service.findAnimal(Integer.parseInt(id));
        if (animalToUpdate == null){
            ViewData.addAttribute("error","El animal con id " + id + " no existe");
            ViewData.addAttribute("animalList",service.getAnimalList());
            return "/animals/index";
        }
        return "/animals/update";
    }
}
