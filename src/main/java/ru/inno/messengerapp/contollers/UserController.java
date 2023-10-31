package ru.inno.messengerapp.contollers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.inno.messengerapp.dto.request.UserRequest;
import ru.inno.messengerapp.models.User;
import ru.inno.messengerapp.repositories.UserRepository;

import java.util.Optional;

@RestController
@RequestMapping("/api/users/")

public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public User getUserByEmail(
            @RequestParam(name = "email") String email,
            @RequestParam(name = "password") String password) {
        Optional<User> user = userRepository.findByEmailAndPassword(email, User.hashPassword(password));

        if (user.isEmpty()) {
            throw new RuntimeException("Пользователь с таким email не существует");
        }

        return user.get();
    }

    @PostMapping
    public ResponseEntity<Void> createUser(
            @RequestBody @Validated UserRequest userRequest) {
        User user = new User(userRequest);
        userRepository.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @DeleteMapping
    public ResponseEntity<Void> deleteUses(
            @RequestParam(name = "email") String email) {
        userRepository.deleteAllByEmail(email);
        return new ResponseEntity<>(HttpStatus.OK);


    }
}
