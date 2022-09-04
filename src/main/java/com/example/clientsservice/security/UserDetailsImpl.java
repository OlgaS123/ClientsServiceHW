package com.example.clientsservice.security;

import com.example.clientsservice.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {
    public User user;

    public UserDetailsImpl(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.stream(User.Role.values())
                .map(role->new SimpleGrantedAuthority(role.name()))
                .collect(Collectors.toSet());
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.getStatus().equals(User.Status.ACTIVATED);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return !user.getStatus().equals(User.Status.BLOCKED);
    }
}
