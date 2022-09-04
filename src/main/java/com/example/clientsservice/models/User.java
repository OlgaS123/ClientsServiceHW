package com.example.clientsservice.models;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "users")
public class User {
    public enum Status{
        BLOCKED, ACTIVATED,DELETED
    }
    public enum Role implements GrantedAuthority {
        USER,ADMIN;

        @Override
        public String getAuthority() {
            return name();
        }

        public static Collection<GrantedAuthority> authorities(){
            return List.of(values());
        }
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, length = 50)
    private String username;
    @Column(nullable = false, length = 50)
    private String password;
    @Column(nullable = false)
    private String mail;
    @Column(nullable = false)
    private Status status;
    @Column(nullable = false)
    private Role role;
    {
        status=Status.ACTIVATED;
        role=Role.USER;
    }

}
