package io.fam.famapi.model;

import lombok.Data;
import lombok.Builder;
import jakarta.persistence.*;
import java.util.UUID;
import java.sql.Timestamp;

@Entity
@Table(name = "users")
@Data
@Builder
public class User {
    @Id
    @GeneratedValue
    @Column(name = "id", updatable = false)
    private UUID id;

    private String username;
    private String password;
    private boolean enabled;
    
}
