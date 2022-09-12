package com.example.clientsservice.ui.controllers;

import com.example.clientsservice.models.Account;
import com.example.clientsservice.models.Client;
import com.example.clientsservice.models.Phone;
import com.example.clientsservice.services.data.AccountService;
import com.example.clientsservice.services.data.ClientService;
import com.example.clientsservice.services.data.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
public class ClientUpdateController {
    @Qualifier("clientServiceDb")
    @Autowired
    private ClientService clientService;
    @Qualifier("phoneServiceDb")
    @Autowired
    private PhoneService phoneService;
    @Qualifier("accountServiceDb")
    @Autowired
    private AccountService accountService;
    Client client;

    @GetMapping("clientUpdate")
    public String loadClients(Model model, @RequestParam("id") Integer id){
        client = clientService.findById(id);
        model.addAttribute("client", client);
        model.addAttribute("phones", client.getPhones());
        model.addAttribute("accounts", client.getAccounts());
        //for mustache:
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

    /*@PostMapping("clientUpdateForm")
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
    }*/

    @PostMapping("clientUpdateForm")
    public String clientUpdateForm(
            @ModelAttribute("client") Client client
    ){
        clientService.save(client);
        return "redirect:/clients";
    }

    @PostMapping("addPhoneForm")
    public ModelAndView addPhoneForm(
            @ModelAttribute("phone") Phone phone,
            @RequestParam("clientId") Integer id
    ){
        Client client = clientService.findById(id);
        phone.setClient(client);
        phoneService.save(phone);
        return new ModelAndView("redirect:clientUpdate",
                new ModelMap("id",id));
    }

    @PostMapping("deletePhoneForm")
    public ModelAndView updatePhoneForm(
            @RequestParam("clientId") Integer clientId,
            @RequestParam("phoneDelete") Integer phoneId
    ){
        phoneService.deleteByID(phoneId);
        return new ModelAndView("redirect:clientUpdate",
                new ModelMap("id",clientId));
    }

    @PostMapping("addAccountForm")
    public ModelAndView addAccountForm(
            @ModelAttribute("account") String acc,
            @RequestParam("clientId") Integer id
    ){
        Client client = clientService.findById(id);
        Account account = new Account(0L, Double.parseDouble(acc), null);
        account=accountService.save(account);
        client.getAccounts().add(account);
        clientService.save(client);
        return new ModelAndView("redirect:clientUpdate",
                new ModelMap("id",id));
    }

    @PostMapping("delAccountForm")
    public ModelAndView delAccountForm(
            @RequestParam("clientId") Integer clientId,
            @RequestParam("accountDelete") Long id
    ){
        Client client = clientService.findById(clientId);
        client.getAccounts().removeIf(account -> account.getId()==id);
        clientService.save(client);
        return new ModelAndView("redirect:clientUpdate",
                new ModelMap("id",clientId));
    }

    @PostMapping("updAccountForm")
    public String updAccountForm(
            @RequestParam("clientId") Integer clientId,
            @RequestParam("accountUpdate") Long id
    ){
        //not working =(
        return "";
    }
}
