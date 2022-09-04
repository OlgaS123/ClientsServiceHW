package com.example.clientsservice.ui.controllers;

import com.example.clientsservice.models.User;
import com.example.clientsservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UsersController {
    @Autowired
    private UserService userService;
    @GetMapping("users")
    String usersLoad(Model model){
        List<User> list = userService.findAll();
        model.addAttribute("users", list);
        return "users";
    }
}
