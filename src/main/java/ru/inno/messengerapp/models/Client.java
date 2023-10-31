package ru.inno.messengerapp.models;

import jakarta.persistence.*;

import lombok.*;
import ru.inno.messengerapp.dto.request.ClientRequest;
import ru.inno.messengerapp.dto.request.UserRequest;

import java.util.ArrayList;
import java.util.List;

@Data
@Table
@Entity
public class Client {
    public Client(ClientRequest clientRequest) {
        this.firstName = clientRequest.getFirstName();
        this.lastName = clientRequest.getLastName();
        this.email = clientRequest.getEmail();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Book> books = new ArrayList<>();

    public Client() {

    }

    public Client(Long clientId) {
    }
}
