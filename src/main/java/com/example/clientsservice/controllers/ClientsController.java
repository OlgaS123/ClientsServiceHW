package com.example.clientsservice.controllers;

import com.example.clientsservice.models.Client;
import com.example.clientsservice.services.data.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.List;

@Controller
public class ClientsController {
    @Qualifier("clientServiceDb")
    @Autowired
    private ClientService clientService;

    @GetMapping("/")
    public String load(Model model){
        return "redirect:clients";
    }

    @GetMapping("clients")
    public String loadClients(Model model){
        List<Client> list = clientService.findAll();
        model.addAttribute("clients", list);
        return "clients";
    }

    @PostMapping("clientForm")
    public String clientForm(
            @RequestParam("name") String name,
            @RequestParam("surname") String surname,
            @RequestParam("birthday") String birthday,
            @RequestParam("gender") Client.Gender gender,
            @RequestParam("email") String email
            ){
        Client client = new Client(0,name,surname, LocalDate.parse(birthday),gender,null,email,null);
        clientService.save(client);
        return "redirect:/clients";
    }
    @PostMapping("clientUpdate")
    public ModelAndView clientUpdate(@RequestParam("id") Integer id){

        return new ModelAndView("redirect:clientUpdate", new ModelMap("id", id));
    }

}
