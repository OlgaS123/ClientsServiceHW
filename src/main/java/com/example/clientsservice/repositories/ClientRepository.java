package com.example.clientsservice.repositories;

import com.example.clientsservice.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    List<Client> findAllByNameAndSurname(String name, String surname);
}
