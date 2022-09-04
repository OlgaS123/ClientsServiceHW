package com.example.clientsservice.services.data.db;

import com.example.clientsservice.models.User;
import com.example.clientsservice.services.data.UserService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserServiceDbTest {
    private static User admin = new User(0,"a","p","a@a", User.Status.ACTIVATED, User.Role.ADMIN);
    private static User user = new User(0,"u","p","u@u", User.Status.ACTIVATED, User.Role.USER);
    @Autowired
    private UserService userService;
    @Autowired
    BCryptPasswordEncoder encoder;

    @Test
    @Order(1)
    public void deleteAll(){
        userService.deleteAll();
    }
    @Test
    @Order(2)
    public void addUsers(){
        admin.setPassword(encoder.encode(admin.getPassword()));
        user.setPassword(encoder.encode(user.getPassword()));
        admin=userService.save(admin);
        user=userService.save(user);
    }
    @Test
    @Order(3)
    public void findAll(){
        userService.findAll().forEach(System.err::println);
    }
}
