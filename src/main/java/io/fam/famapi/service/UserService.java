package io.fam.famapi.service;

import io.fam.famapi.repository.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.sql.Timestamp;
import io.fam.famapi.model.User;

/**
 * Service class for managing users.
 */
@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Retrieves all users.
     *
     * @return a list of all users
     */
    public List<User> getAllUsers() {
        logger.info("Fetching all users");
        return userRepository.findAll();
    }

    /**
     * Creates a new user.
     *
     * @param user the user to create
     * @return the created user
     */
    public User createUser(User user) {
        logger.info("Creating new user with username: {}", user.getUsername());
        return userRepository.save(user);
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param id the ID of the user to retrieve
     * @return the user with the specified ID, or null if not found
     */
    public User getUserById(UUID id) {
        logger.info("Fetching user with ID: {}", id);
        return userRepository.findById(id).orElse(null);
    }

    /**
     * Updates an existing user.
     *
     * @param id the ID of the user to update
     * @param user the user data to update
     * @return the updated user, or null if the user was not found
     */
    public User updateUser(UUID id, User user) {
        logger.info("Updating user with ID: {}", id);
        User existingUser = userRepository.findById(id).orElse(null);
        if (existingUser == null) {
            logger.warn("User with ID: {} not found", id);
            return null;
        }
        existingUser.setUsername(user.getUsername());
        existingUser.setPassword(user.getPassword());
        User updatedUser = userRepository.save(existingUser);
        logger.info("User with ID: {} updated successfully", id);
        return updatedUser;
    }
}
