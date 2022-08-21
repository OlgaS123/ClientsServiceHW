package com.example.clientsservice;

import com.example.clientsservice.repositories.ClientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ClientsServiceApplicationTests {

    @Autowired
    ClientRepository clientRepository;

    @Test
    void contextLoads() {
        Assertions.assertNotNull(clientRepository);
    }

}
