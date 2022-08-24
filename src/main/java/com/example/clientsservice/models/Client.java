package com.example.clientsservice.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

//
@Entity
@Table(name = "clients")
public class Client {
    public enum Gender{
        NONE, MALE, FEMALE
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, length = 50)
    private String name;
    @Column(nullable = false, length = 50)
    private String surname;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private LocalDate birthday;
    @Column
    private Gender gender;
    //@Column(nullable = false, length = 50, unique = true)
    //private String phone;
    @OneToMany(mappedBy = "client",fetch = FetchType.EAGER)
    private Set<Phone> phones;
    @Column(nullable = false, length = 50)
    private String email;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "clients_accounts",
        joinColumns = @JoinColumn(
                name = "client_id",
                referencedColumnName = "id",
                foreignKey = @ForeignKey(
                        name = "clientKey",
                        value = ConstraintMode.CONSTRAINT
                )
        ), inverseJoinColumns = @JoinColumn(
            name = "account_id",
            referencedColumnName = "id"
        )
    )
    private Set<Account> accounts;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return id.equals(client.id) && name.equals(client.name) && surname.equals(client.surname) && birthday.equals(client.birthday) && gender == client.gender && email.equals(client.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, birthday, gender, email);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthday=" + birthday +
                ", gender=" + gender +
                ", email='" + email + '\'' +
                '}';
    }
}
