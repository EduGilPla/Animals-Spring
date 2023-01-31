package cifpcm.es.AnimalsApp.controllers;

import cifpcm.es.AnimalsApp.models.User;
import cifpcm.es.AnimalsApp.services.UserServiceDB;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
@Controller
public class AuthController {
  @Autowired
  UserServiceDB userService;
  @GetMapping("/login")
  public String Login(){
    return "/authentication/login";
  }
  @GetMapping("/register")
  public String Register(Model ViewData){
    ViewData.addAttribute("user", new User());
    return "/authentication/register";
  }
  @PostMapping("/register")
  public String Register(@Valid @ModelAttribute("newUser") User newUser, BindingResult bindingResult, Model ViewData){
    if(bindingResult.hasErrors())
      return "/authentication/register";
    if(userService.registerUser(newUser))
      return "redirect:/";
    ViewData.addAttribute("error","Putada");
    return "/authentication/register";
  }
}
