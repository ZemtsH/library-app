package ru.inno.messengerapp.contollers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.inno.messengerapp.dto.request.ClientRequest;
import ru.inno.messengerapp.dto.request.UserRequest;
import ru.inno.messengerapp.models.Client;
import ru.inno.messengerapp.models.User;
import ru.inno.messengerapp.repositories.ClientRepository;
import ru.inno.messengerapp.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clients/")

public class ClientController {
    @Autowired
    private ClientRepository clientRepository;

    @PostMapping
    public ResponseEntity<Void> createClient(
            @RequestBody @Validated ClientRequest clientRequest) {
        Client client = new Client(clientRequest);
        clientRepository.save(client);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @DeleteMapping
    public ResponseEntity<Void> deleteClient(
            @RequestParam(name = "id") Long id) {
        clientRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("{id}")
    public Client getClientById(@PathVariable(name = "id") Long id) {
        Optional<Client> client = clientRepository.findById(id);

        if (client.isEmpty()) {
            throw new RuntimeException("Такого клиента не существует");
        }
        return client.get();
    }

    @GetMapping
    public List<Client> getClientById() {

        return clientRepository.findAll();
    }
}
