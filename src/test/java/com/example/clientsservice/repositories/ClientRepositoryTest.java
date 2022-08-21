package com.example.clientsservice.repositories;

import com.example.clientsservice.models.Client;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class ClientRepositoryTest {
    @Autowired
    ClientRepository clientRepository;
    static Client client1, client2;
    @Order(1)
    @Test
    void insertTest(){
        client1=new Client(0,"q","w", LocalDate.now(),"e","rty");
        client1=clientRepository.save(client1);
    }
    @Order(2)
    @Test
    void readTest(){
        client2=clientRepository.findById(client1.getId()).get();
        Assertions.assertEquals(client1, client2);
    }
    @Order(3)
    @Test
    void updateTest(){
        client1.setName("new");
        client2 = clientRepository.save(client1);
        Assertions.assertEquals(client1, client2);
    }
    @Order(4)
    @Test
    void deleteTest(){
        clientRepository.delete(client1);
        client2 = clientRepository.findById(client1.getId()).orElse(null);
        Assertions.assertNull(client2);
    }
}
