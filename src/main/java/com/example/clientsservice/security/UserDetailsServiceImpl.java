package com.example.clientsservice.security;

import com.example.clientsservice.models.User;
import com.example.clientsservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);
        System.err.println("loadUserByUsername " + user);
        if (user == null)
            throw new UsernameNotFoundException("User not found");
        return new UserDetailsImpl(user);
    }
}

