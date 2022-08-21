package com.example.clientsservice.services.data.db;

import com.example.clientsservice.models.Phone;
import com.example.clientsservice.services.data.PhoneService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class PhoneServiceDbTest {
    @Qualifier("phoneServiceDb")
    @Autowired
    PhoneService phoneService;
    static Phone phone1,phone2;
    static List<Phone> list;
    @Order(1)
    @Test
    void insertTest(){
        phone1=new Phone(0,"qwerty",null);
        phone2=phoneService.save(phone1);
        assertNotNull(phone2);
    }
    @Order(2)
    @Test
    void readAllTest(){
        list=phoneService.findAll();
        assertNotNull(list);
    }

}
