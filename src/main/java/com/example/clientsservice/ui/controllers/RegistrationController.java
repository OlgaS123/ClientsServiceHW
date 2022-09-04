package com.example.clientsservice.ui.controllers;

import com.example.clientsservice.models.User;
import com.example.clientsservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {
    @Autowired
    private UserService userService;
    @GetMapping("/registration")
    String registerLoad(){
        return "registration";
    }
    @PostMapping("/registration")
    public String registerForm(@ModelAttribute("user") User user){
        user=userService.save(user);
        return "redirect:registration";
    }
}
