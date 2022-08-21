package com.example.clientsservice.services.data.json;

import com.example.clientsservice.models.Client;
import com.example.clientsservice.services.data.ClientService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class ClientServiceJsonTest {

    @Qualifier("clientServiceJson")
    @Autowired
    ClientService clientService;

    @Order(2)
    @Test
    void readAllTest() {
        List<Client> list=clientService.findAll();
        System.err.println(list);
    }

    @Order(1)
    @Test
    void saveTest() {
        List<Client> list= Arrays.asList(
                new Client(1,"q","w", LocalDate.now(), Client.Gender.NONE,null,"rty"),
                new Client(2,"q","w", LocalDate.now(), Client.Gender.NONE,null,"rty")
        );
        clientService.save(list.get(0));
        clientService.save(list.get(1));
    }
}
