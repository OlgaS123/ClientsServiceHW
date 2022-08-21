package com.example.clientsservice.services.data;

import com.example.clientsservice.models.Client;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ClientService {
    Client save(Client client);

    Client findById(Integer id);

    List<Client> findAll();

    void delete(Client client);
}
