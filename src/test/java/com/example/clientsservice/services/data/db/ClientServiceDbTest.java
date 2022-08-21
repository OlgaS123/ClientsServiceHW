package com.example.clientsservice.services.data.db;

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
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class ClientServiceDbTest {
    @Qualifier("clientServiceDb")
    @Autowired
    ClientService clientService;
    static Client client1,client2;
    static List<Client> list;
    @Order(1)
    @Test
    void insertTest(){
        client1=new Client(0,"q","w", LocalDate.now(),"e","rty");
        client2=clientService.save(client1);
        assertNotNull(client2);
    }
    @Order(2)
    @Test
    void readAllTest(){
        list=clientService.findAll();
        assertNotNull(list);
    }

}