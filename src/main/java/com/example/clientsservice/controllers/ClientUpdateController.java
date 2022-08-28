package com.example.clientsservice.controllers;

import com.example.clientsservice.models.Client;
import com.example.clientsservice.services.data.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ClientUpdateController {
    @Qualifier("clientServiceDb")
    @Autowired
    private ClientService clientService;
    Client client;

    @GetMapping("clientUpdate")
    public String loadClients(Model model, @RequestParam("client") Integer id){
        client = clientService.findById(id);
        model.addAttribute("client", client);
        Map<Client.Gender, String> genderValues = new HashMap<>();
        for (Client.Gender g : Client.Gender.values()) {
            if (client.getGender()==g)
                genderValues.put(g, "selected");
            else
                genderValues.put(g, "");
        }
        model.addAttribute("genderValues", genderValues.entrySet());
        return "clientUpdate";
    }

    @PostMapping("clientUpdateForm")
    public String clientUpdateForm(
            @RequestParam("name") String name,
            @RequestParam("surname") String surname,
            @RequestParam("birthday") String birthday,
            @RequestParam("gender") Client.Gender gender,
            @RequestParam("email") String email
    ){
        if(client==null)client=new Client();
        client.setName(name);
        client.setSurname(surname);
        client.setBirthday(LocalDate.parse(birthday));
        client.setGender(gender);
        client.setEmail(email);
        clientService.save(client);
        return "redirect:/clients";
    }
}
