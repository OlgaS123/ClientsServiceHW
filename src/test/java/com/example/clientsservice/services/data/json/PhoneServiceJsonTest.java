package com.example.clientsservice.services.data.json;

import com.example.clientsservice.models.Phone;
import com.example.clientsservice.services.data.PhoneService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class PhoneServiceJsonTest {

    @Qualifier("phoneServiceJson")
    @Autowired
    PhoneService phoneService;

    @Order(2)
    @Test
    void readAllTest() {
        List<Phone> list=phoneService.findAll();
        System.err.println(list);
    }

    @Order(1)
    @Test
    void Test() {
        List<Phone> list= Arrays.asList(
                new Phone(1,"111111111"),
                new Phone(2,"222222222")
        );
        phoneService.save(list.get(0));
        phoneService.save(list.get(1));
    }
}
