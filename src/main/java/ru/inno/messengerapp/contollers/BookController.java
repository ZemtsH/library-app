package ru.inno.messengerapp.contollers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.inno.messengerapp.dto.request.BookRequest;
import ru.inno.messengerapp.dto.request.UserRequest;
import ru.inno.messengerapp.models.Book;
import ru.inno.messengerapp.models.Client;
import ru.inno.messengerapp.models.User;
import ru.inno.messengerapp.repositories.BookRepository;
import ru.inno.messengerapp.repositories.ClientRepository;
import ru.inno.messengerapp.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books/")

public class BookController {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private ClientRepository clientRepository;

    @GetMapping
    public List<Book> getAllBooks(@RequestParam(name="client_id", required = false) Long clientId) {
        if (clientId==null){
            return bookRepository.findAll();
        }
        return bookRepository.findByClientId(clientId);

    }

    @PostMapping
    public ResponseEntity<Void> createBook(
            @RequestBody @Validated BookRequest bookRequest) {
        Book book = new Book(bookRequest);
        bookRepository.save(book);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @DeleteMapping
    public ResponseEntity<Void> deleteBook(
            @RequestParam(name = "id") Long id) {
        bookRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PatchMapping("{id}")
    public ResponseEntity<Void> updateBook(
            @RequestBody @Validated BookRequest bookRequest, @PathVariable(name = "id") Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isEmpty()){
            throw new RuntimeException("Такой книги не существует");
        }
        Optional<Client> client = clientRepository.findById(bookRequest.getClientId());
        Book updateBook = book.get();
        updateBook.setAuthor(bookRequest.getAuthor());
        updateBook.setName(bookRequest.getName());
        client.ifPresent(updateBook::setClient);

        bookRepository.save(book.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("{id}")
    public Book getBookById (@PathVariable(name = "id") Long id) {
        Optional<Book> book = bookRepository.findById(id);

        if (book.isEmpty()) {
            throw new RuntimeException("Такой книги не существует");
        }
        return book.get();
    }
}
