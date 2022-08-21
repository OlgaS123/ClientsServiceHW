package com.example.clientsservice.services.data.db;

import com.example.clientsservice.models.Account;
import com.example.clientsservice.services.data.AccountService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class AccountServiceDbTest {
    @Qualifier("accountServiceDb")
    @Autowired
    AccountService accountService;
    static Account account1,account2;
    static List<Account> list;
    @Order(1)
    @Test
    void insertTest(){
        account1=new Account(0L,100D);
        account2=accountService.save(account1);
        assertNotNull(account2);
    }
    @Order(2)
    @Test
    void readAllTest(){
        list=accountService.findAll();
        assertNotNull(list);
    }

}