package com.example.clientsservice.services.data.db;

import com.example.clientsservice.models.Account;
import com.example.clientsservice.models.Client;
import com.example.clientsservice.services.data.AccountService;
import com.example.clientsservice.services.data.ClientService;
import com.example.clientsservice.services.data.qualifiers.AccountServiceQualifier;
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
public class ClientsAccountsDbTest {
    Client client1,client2;
    Account account1, account2;
    @Qualifier("clientServiceDb")
    @Autowired
    ClientService clientService;
    @AccountServiceQualifier
    @Autowired
    AccountService accountService;
    @Order(1)
    @Test
    void saveClientsAccounts(){
        client1=new Client(0,"a","w", LocalDate.now(), Client.Gender.NONE,null,"rty",null);
        client2=new Client(0,"b","w", LocalDate.now(), Client.Gender.NONE,null,"rty",null);
        account1=new Account(0L,100D, null);
        account2=new Account(0L,50D, null);

        account1=accountService.save(account1);
        account2=accountService.save(account2);

        client1.setAccounts(Set.of(account1,account2));
        client2.setAccounts(Set.of(account1,account2));
        client1=clientService.save(client1);
        client2=clientService.save(client2);

        System.err.println(client1.getAccounts());
    }

    @Order(2)
    @Test
    void updateAccounts(){
        client1=new Client(0,"a","w", LocalDate.now(), Client.Gender.NONE,null,"rty",null);
        account1=new Account(0L,100D, null);
        account2=new Account(0L,50D, null);

        account1=accountService.save(account1);
        account2=accountService.save(account2);

        client1.setAccounts(Set.of(account1,account2));
        client1=clientService.save(client1);

        System.err.println(client1.getAccounts());

        //client1.getAccounts().stream().filter(account -> account.getId()==1).findFirst().get().setAmount(200D);
        client1=clientService.save(client1);

        System.err.println(client1.getAccounts());
    }

    @Order(3)
    @Test
    void removeAccountsFromClients(){
        client1=new Client(0,"a","w", LocalDate.now(), Client.Gender.NONE,null,"rty",null);
        account1=new Account(0L,100D, null);
        account2=new Account(0L,50D, null);

        account1=accountService.save(account1);
        account2=accountService.save(account2);

        client1.setAccounts(Set.of(account1,account2));
        client1=clientService.save(client1);

        System.err.println(client1.getAccounts());

        client1.getAccounts().clear();
        client1=clientService.save(client1);

        System.err.println(client1.getAccounts());
    }
}
