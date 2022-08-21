package com.example.clientsservice.services.data.json;

import com.example.clientsservice.models.Account;
import com.example.clientsservice.models.Client;
import com.example.clientsservice.services.data.ClientService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
@Service
public class ClientServiceJson implements ClientService {
    private final Path path=Path.of("clients.json");
    private Gson gson(){
        return new Gson();
    }

    {
        ArrayList<Client> list = new ArrayList<>();
        String json = gson().toJson(list);
        try{
            Files.writeString(path,json, StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE);
        } catch (IOException e) {
            //throw new RuntimeException(e);
        }
    }
    @Override
    public List<Client> findAll(){
        try {
            String json = Files.readString(path);
            return gson().fromJson(json,new TypeToken<List<Client>>(){}.getType());
        }
        catch (IOException e){
            //throw new RuntimeException();
        }
        return new ArrayList<>();
    }
    @Override
    public Client save(Client client) {
        List<Client> list = findAll();
        list.add(client);
        String json = gson().toJson(list);
        try{
            Files.writeString(path,json, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
            return client;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Client findById(Integer id) {
        List<Client> list = findAll();
        return list.stream().filter(client -> client.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public void delete(Client client) {
        List<Client> list = findAll();
        list.remove(client);
        String json = gson().toJson(list);
        try{
            Files.writeString(path,json, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Client> findAllByNameAndSurname(String name, String surname) {
        return null;
    }
}
