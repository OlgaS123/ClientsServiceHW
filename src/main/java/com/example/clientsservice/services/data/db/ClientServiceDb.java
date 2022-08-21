package com.example.clientsservice.services.data.db;

import com.example.clientsservice.models.Client;
import com.example.clientsservice.repositories.ClientRepository;
import com.example.clientsservice.services.data.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceDb implements ClientService {
    @Autowired
    private ClientRepository clientRepository;
    @Override
    public Client save(Client client){
        return clientRepository.save(client);
    }
    @Override
    public Client findById(Integer id){
        return clientRepository.findById(id).get();
    }
    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }
    @Override
    public void delete(Client client){
        clientRepository.delete(client);
    }
}
