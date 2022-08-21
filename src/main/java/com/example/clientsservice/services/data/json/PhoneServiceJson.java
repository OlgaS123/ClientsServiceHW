package com.example.clientsservice.services.data.json;

import com.example.clientsservice.models.Client;
import com.example.clientsservice.models.Phone;
import com.example.clientsservice.services.data.PhoneService;
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
public class PhoneServiceJson implements PhoneService {
    private final Path path=Path.of("phones.json");
    private Gson gson(){
        return new Gson();
    }

    {
        ArrayList<Phone> list = new ArrayList<>();
        String json = gson().toJson(list);
        try{
            Files.writeString(path,json, StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE);
        } catch (IOException e) {
            //throw new RuntimeException(e);
        }
    }
    @Override
    public List<Phone> findAll(){
        try {
            String json = Files.readString(path);
            return gson().fromJson(json,new TypeToken<List<Phone>>(){}.getType());
        }
        catch (IOException e){
            //throw new RuntimeException();
        }
        return new ArrayList<>();
    }
    @Override
    public Phone save(Phone phone) {
        List<Phone> list = findAll();
        list.add(phone);
        String json = gson().toJson(list);
        try{
            Files.writeString(path,json, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
            return phone;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Phone findById(Integer id) {
        List<Phone> list = findAll();
        return list.stream().filter(phone -> phone.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public void delete(Phone phone) {
        List<Phone> list = findAll();
        list.remove(phone);
        String json = gson().toJson(list);
        try {
            Files.writeString(path, json, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
