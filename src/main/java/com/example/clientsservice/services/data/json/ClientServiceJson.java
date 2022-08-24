package com.example.clientsservice.services.data.json;

import com.example.clientsservice.models.Account;
import com.example.clientsservice.models.Client;
import com.example.clientsservice.models.Phone;
import com.example.clientsservice.services.data.ClientService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Service
public class ClientServiceJson implements ClientService {
    private final Path path=Path.of("clients.json");
    private Gson gson(){
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Client.class, new TypeAdapter<Client>(){

            @Override
            public void write(JsonWriter jsonWriter, Client client) throws IOException {
                jsonWriter.beginObject();
                jsonWriter.name("id").value(client.getId());
                //jsonWriter.name("name").value(client.getId());
                //jsonWriter.name("surname").value(client.getId());
                jsonWriter.name("birthdate").value(client.getBirthday().toString());
                jsonWriter.name("phones").beginArray();
                if(client.getPhones()!=null){
                    for(Phone p:client.getPhones()){
                        jsonWriter.beginObject();
                        jsonWriter.name("id").value(p.getId());
                        jsonWriter.name("phone").value(p.getPhone());
                        jsonWriter.name("client").value(p.getClient().getId());
                        jsonWriter.endObject();
                    }
                }
                jsonWriter.endArray();
                jsonWriter.endObject();
            }

            @Override
            public Client read(JsonReader jsonReader) throws IOException {
                Client client = new Client();
                jsonReader.beginObject();
                jsonReader.nextName();
                client.setId(jsonReader.nextInt());
                ///...
                jsonReader.nextName();
                client.setBirthday(LocalDate.parse(jsonReader.nextString()));
                ///...
                jsonReader.nextName();
                jsonReader.beginArray();
                while (jsonReader.hasNext())
                {
                    Phone p = new Phone();
                    jsonReader.beginObject();
                    jsonReader.nextName();
                    p.setId(jsonReader.nextInt());
                    jsonReader.nextName();
                    p.setPhone(jsonReader.nextString());
                    jsonReader.endObject();
                    client.getPhones().add(p);
                }
                jsonReader.endArray();
                jsonReader.endObject();
                return client;
            }
        });
        return builder.create();
    }

    {
        ArrayList<Client> list = new ArrayList<>();
        String json = gson().toJson(list);
        try{
            Files.writeString(path,json, StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE);
        } catch (Exception e) {
            //throw new RuntimeException(e);
        }
    }
    @Override
    public List<Client> findAll(){
        try {
            String json = Files.readString(path);
            return gson().fromJson(json,new TypeToken<List<Client>>(){}.getType());
        }
        catch (Exception e){
            //throw new RuntimeException();
        }
        return new ArrayList<>();
    }
    @Override
    public Client save(Client client) {
        List<Client> list = findAll();
        list.add(client);
        System.err.println(list);
        String json = gson().toJson(list);
        System.err.println(json);
        try{
            Files.writeString(path,json, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
            return client;
        } catch (Exception e) {
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
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Client> findAllByNameAndSurname(String name, String surname) {
        return null;
    }
}
