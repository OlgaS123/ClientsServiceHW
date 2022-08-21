package com.example.clientsservice.services.data.db;

import com.example.clientsservice.models.Client;
import com.example.clientsservice.models.Phone;
import com.example.clientsservice.services.data.ClientService;
import com.example.clientsservice.services.data.PhoneService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class ClientServiceDbTest {
    @Qualifier("clientServiceDb")
    @Autowired
    ClientService clientService;
    @Qualifier("phoneServiceDb")
    @Autowired
    PhoneService phoneService;
    static Client client1,client2;
    static List<Client> list;
    @Order(1)
    @Test
    void insertTest(){
        client1=new Client(0,"q","w", LocalDate.now(), Client.Gender.NONE,null,"rty");
        client2=clientService.save(client1);
        assertNotNull(client2);
    }
    @Order(2)
    @Test
    void readAllTest(){
        list=clientService.findAll();
        assertNotNull(list);
    }

    @Order(3)
    @Test
    void findAllByNameAndSurnameTest(){
        list=clientService.findAllByNameAndSurname(client1.getName(), client1.getSurname());
        list.forEach(System.err::println);
        //1
        Assertions.assertNotEquals(list.size(),0);
        //2
        boolean match = list.stream().allMatch(client -> client.getName().equals(client1.getName())
        && client.getSurname().equals(client1.getSurname()));
        Assertions.assertTrue(match);
    }
    @Order(4)
    @Test
    void saveClientPhones(){
        client1=new Client(0,"q","w", LocalDate.now(), Client.Gender.NONE,null,"rty");
        client1=clientService.save(client1);
        Phone p1 = new Phone(0, "111", client1);
        Phone p2 = new Phone(0, "222", client1);
        p1=phoneService.save(p1);
        p2=phoneService.save(p2);
        if(client1.getPhones()==null)
            client1.setPhones(new HashSet<>());
        client1.getPhones().add(p1);
        client1.getPhones().add(p2);
        client1=clientService.save(client1);
    }



}