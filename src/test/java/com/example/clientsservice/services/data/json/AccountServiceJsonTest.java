package com.example.clientsservice.services.data.json;

import com.example.clientsservice.models.Account;
import com.example.clientsservice.services.data.AccountService;

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
class AccountServiceJsonTest {

    @Qualifier("accountServiceJson")
    @Autowired
    AccountService accountService;

    @Order(2)
    @Test
    void readAllTest() {
        List<Account> list=accountService.findAll();
        System.err.println(list);
    }

    @Order(1)
    @Test
    void saveTest() {
        List<Account> list=Arrays.asList(
                new Account(1L,100D),
                new Account(2L,200D)
        );
        accountService.save(list.get(0));
        accountService.save(list.get(1));
    }
}