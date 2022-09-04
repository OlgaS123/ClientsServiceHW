package com.example.clientsservice.security;

import com.example.clientsservice.models.User;
import com.example.clientsservice.services.data.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthProvider implements AuthenticationProvider {
    @Autowired
    private UserService userService;
    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        User user = userService.findByUsername(username);
        if(user!=null && user.getUsername().equals(username)){
            if(!encoder.matches(password,user.getPassword())){
                throw new BadCredentialsException("Wrong Password");
            }
            return new UsernamePasswordAuthenticationToken(user,password, List.of(user.getRole()));
        }
        else throw new BadCredentialsException("Username not found");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
