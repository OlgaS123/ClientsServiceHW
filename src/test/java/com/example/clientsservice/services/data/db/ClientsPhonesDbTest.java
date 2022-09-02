package com.example.clientsservice.services.data.db;

import com.example.clientsservice.models.Account;
import com.example.clientsservice.models.Client;
import com.example.clientsservice.models.Phone;
import com.example.clientsservice.services.data.ClientService;
import com.example.clientsservice.services.data.PhoneService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Set;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class ClientsPhonesDbTest {
    Client client1,client2;
    Phone phone1, phone2, phone3;
    @Qualifier("clientServiceDb")
    @Autowired
    ClientService clientService;
    @Qualifier("phoneServiceDb")
    @Autowired
    PhoneService phoneService;

    @Order(1)
    @Test
    void saveClientsPhones(){
        client1=new Client(0,"a","w", LocalDate.now(), Client.Gender.NONE,null,"rty",null);
        client2=new Client(0,"b","w", LocalDate.now(), Client.Gender.NONE,null,"rty",null);
        phone1=new Phone(0,"111111", null);
        phone2=new Phone(0,"222222", null);
        phone3=new Phone(0,"333333", null);

        phone1=phoneService.save(phone1);
        phone2=phoneService.save(phone2);
        phone3=phoneService.save(phone3);

        client1.setPhones(Set.of(phone1,phone2));
        client2.setPhones(Set.of(phone3));
        client1=clientService.save(client1);
        client2=clientService.save(client2);

        System.err.println(client1.getPhones());
        System.err.println(client2.getPhones());
    }

    @Order(2)
    @Test
    void removePhonesFromClients(){
        client1=new Client(0,"a","w", LocalDate.now(), Client.Gender.NONE,null,"rty",null);
        phone1=new Phone(0,"111111", null);
        phone2=new Phone(0,"222222", null);
        phone1=phoneService.save(phone1);
        phone2=phoneService.save(phone2);
        client1.setPhones(Set.of(phone1,phone2));
        client1=clientService.save(client1);

        System.err.println(client1.getPhones());

        client1.getPhones().clear();
        client1=clientService.save(client1);

        System.err.println(client1.getPhones());
    }
}
