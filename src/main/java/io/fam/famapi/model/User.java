package io.fam.famapi.model;

import lombok.*;
import jakarta.persistence.*;
import java.util.UUID;
import java.sql.Timestamp;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue
    private UUID id;

    private String username;
    private String passwordHash;
    private String role;

    @Column(name = "created_at", updatable = false)
    private Timestamp createdAt;
}
