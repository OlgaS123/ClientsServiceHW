package com.example.clientsservice.ui.controllers;

import com.example.clientsservice.models.User;
import com.example.clientsservice.services.data.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @PostMapping("userUpdateForm")
    public String userUpdateForm(
            @RequestParam("userUpd") Integer id,
            @RequestParam("status") User.Status status,
            @RequestParam("role") User.Role role
    ){

        User user = userService.findById(id);
        if(user!=null)
        {
            user.setStatus(status);
            user.setRole(role);
            user=userService.save(user);
        }
        return "redirect:/users";
    }
}
