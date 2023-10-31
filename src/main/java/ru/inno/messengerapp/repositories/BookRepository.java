package ru.inno.messengerapp.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.inno.messengerapp.models.Book;
import ru.inno.messengerapp.models.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
    Optional<Book> findById(Long id);

    List<Book> findAll();
    List<Book> findByClientId(Long clientId);
}

