package com.example.clientsservice.services.data.db;

import com.example.clientsservice.models.Client;
import com.example.clientsservice.models.Phone;
import com.example.clientsservice.repositories.PhoneRepository;
import com.example.clientsservice.services.data.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhoneServiceDb implements PhoneService {
    @Autowired
    private PhoneRepository phoneRepository;
    @Override
    public Phone save(Phone phone){
        return phoneRepository.save(phone);
    }
    @Override
    public Phone findById(Integer id){
        return phoneRepository.findById(id).orElse(null);
    }
    @Override
    public List<Phone> findAll() {
        return phoneRepository.findAll();
    }
    @Override
    public void delete(Phone phone){
        phoneRepository.delete(phone);
    }

    @Override
    public void deleteByID(Integer id) {
        phoneRepository.deleteById(id);
    }
}
