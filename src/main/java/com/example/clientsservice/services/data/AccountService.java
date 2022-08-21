package com.example.clientsservice.services.data;

import com.example.clientsservice.models.Account;

import java.util.List;

public interface AccountService {
    List<Account> findAll();

    Account save(Account account);

    Account findById(Long id);

    void delete(Account account);
}
