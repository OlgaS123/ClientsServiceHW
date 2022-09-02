package com.example.clientsservice.services.data;

import com.example.clientsservice.models.Phone;
import org.springframework.stereotype.Service;

import java.util.List;


public interface PhoneService {
    Phone save(Phone phone);

    Phone findById(Integer id);

    List<Phone> findAll();

    void delete(Phone phone);

    void deleteByID(Integer phoneId);
}
