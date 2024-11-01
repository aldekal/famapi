package io.fam.famapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import io.fam.famapi.model.User;

public interface UserRepository extends JpaRepository<User, UUID> {
}
