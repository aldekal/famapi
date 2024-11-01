package io.fam.famapi.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.UUID;
import io.fam.famapi.repository.UserRepository;
import io.fam.famapi.model.User;
import io.fam.famapi.service.UserService;



@RestController
@RequestMapping("/api/users")
@Tag(name = "User", description = "User management APIs")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    @Operation(summary = "get all users", description = "Retrieve a list of all users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    @Operation(summary = "create a new user", description = "Create a new user with the provided details")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping("/{id}")
    @Operation(summary = "get user by ID", description = "Retrieve a user by their unique ID")
    public User getUserById(@PathVariable UUID id) {
        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "update user", description = "Update an existing user with the provided details")
    public User updateUser(@PathVariable UUID id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

}
