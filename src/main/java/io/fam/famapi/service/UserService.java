package io.fam.famapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import java.sql.Timestamp;
import io.fam.famapi.model.User;
import io.fam.famapi.model.UserHistory;
import io.fam.famapi.repository.UserRepository;
import io.fam.famapi.repository.UserHistoryRepository;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserHistoryRepository userHistoryRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User createUser(User user) {
        User savedUser = userRepository.save(user);
        saveUserHistory(savedUser, "INSERT", user.getId());
        return savedUser;
    }

    public User getUserById(UUID id) {
        return userRepository.findById(id).orElse(null);
    }

    public User updateUser(UUID id, User user) {
        user.setId(id);
        User updatedUser = userRepository.save(user);
        saveUserHistory(updatedUser, "UPDATE", user.getId());
        return updatedUser;
    }

    private void saveUserHistory(User user, String changeType, UUID changedBy) {
        UserHistory history = new UserHistory();
        history.setUserId(user.getId());
        history.setUsername(user.getUsername());
        history.setPasswordHash(user.getPasswordHash());
        history.setChangeType(changeType);
        history.setChangedAt(Timestamp.from(java.time.Instant.now()));
        history.setChangedBy(changedBy);
        userHistoryRepository.save(history);
    }
}
