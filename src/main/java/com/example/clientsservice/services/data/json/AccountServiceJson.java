package com.example.clientsservice.services.data.json;

import com.example.clientsservice.models.Account;
import com.example.clientsservice.services.data.AccountService;
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
public class AccountServiceJson implements AccountService {
    private final Path path=Path.of("accounts.json");
    private Gson gson(){
        return new Gson();
    }

    {
        ArrayList<Account> list = new ArrayList<>();
        String json = gson().toJson(list);
        try{
            Files.writeString(path,json, StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE);
        } catch (IOException e) {
            //throw new RuntimeException(e);
        }
    }
    @Override
    public List<Account> findAll(){
        try {
            String json = Files.readString(path);
            return gson().fromJson(json,new TypeToken<List<Account>>(){}.getType());
        }
        catch (IOException e){
            //throw new RuntimeException();
        }
        return new ArrayList<>();
    }
    @Override
    public Account save(Account account) {
        List<Account> list = findAll();
        list.add(account);
        String json = gson().toJson(list);
        try{
            Files.writeString(path,json, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
            return account;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Account findById(Long id) {
        List<Account> list = findAll();
        return list.stream().filter(account -> account.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public void delete(Account account) {
        List<Account> list = findAll();
        list.remove(account);
        String json = gson().toJson(list);
        try{
            Files.writeString(path,json, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteByID(Long id) {

    }
}
