package ru.inno.messengerapp.models;

import jakarta.persistence.*;

import lombok.*;
import ru.inno.messengerapp.dto.request.BookRequest;
import ru.inno.messengerapp.dto.request.UserRequest;

@Data
@NoArgsConstructor
@Table
@Entity
public class Book {
    public Book(BookRequest bookRequest) {
        this.name = bookRequest.getName();
        this.author = bookRequest.getAuthor();
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String author;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;



}

