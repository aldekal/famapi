package io.fam.famapi.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import io.fam.famapi.repository.UserRepository;
import io.fam.famapi.model.User;


@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    // Weitere CRUD-Operationen hinzufügen
}
