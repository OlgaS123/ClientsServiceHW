package com.example.clientsservice;

import com.example.clientsservice.models.*;
import com.example.clientsservice.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.time.LocalDate;

@SpringBootApplication
public class ClientsServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ClientsServiceApplication.class, args);
    }

    @Autowired
    ClientRepository clientRepository;
    @Autowired
    PhoneRepository phoneRepository;
    @Autowired
    AccountRepository accountRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void applicationReady(){
        Client client = new Client(0,"a","b", LocalDate.now(),"p","e");
        client = clientRepository.save(client);
        System.err.println(client);

        client.setName("A");
        clientRepository.save(client);

        System.err.println();
        clientRepository.findAll().forEach(System.err::println);

        clientRepository.delete(client);
        System.err.println();
        clientRepository.findAll().forEach(System.err::println);

        Phone phone = new Phone(0,"123456789");
        phone = phoneRepository.save(phone);
        System.err.println(phone);

        phone.setPhone("1111111111111111");
        phoneRepository.save(phone);

        System.err.println();
        phoneRepository.findAll().forEach(System.err::println);

        phoneRepository.delete(phone);
        System.err.println();
        phoneRepository.findAll().forEach(System.err::println);

        Account account = new Account(0L,100.50);
        account = accountRepository.save(account);
        System.err.println(account);

        account.setAmount(50D);
        accountRepository.save(account);

        System.err.println();
        accountRepository.findAll().forEach(System.err::println);

        accountRepository.delete(account);
        System.err.println();
        accountRepository.findAll().forEach(System.err::println);
    }

}
