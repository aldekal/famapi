package io.fam.famapi.model;

import lombok.*;
import jakarta.persistence.*;
import java.util.UUID;
import java.sql.Timestamp;

@Entity
@Table(name = "users_hst")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID hstId;

    private UUID userId;
    private String username;
    private String passwordHash;
    private String changeType;
    private Timestamp changedAt;
    private UUID changedBy;

}
