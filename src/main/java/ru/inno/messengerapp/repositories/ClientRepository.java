package ru.inno.messengerapp.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.inno.messengerapp.models.Client;
import ru.inno.messengerapp.models.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {
    List<Client> findAll();
    void deleteById(Long id);

    Optional<Client> findById(Long id);

}

