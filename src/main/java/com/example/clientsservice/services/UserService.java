package com.example.clientsservice.services;

import com.example.clientsservice.models.User;

import java.util.List;

public interface UserService {
    User findByUsername(String username);

    List<User> findAll();

    User save(User user);

    void deleteAll();
}
