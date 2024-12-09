package io.fam.famapi.model;

import lombok.*;
import jakarta.persistence.*;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "person")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Person entity representing a person in the system")
public class Person {
    @Id
    @GeneratedValue
    @Schema(description = "Unique identifier of the person", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID id;

    @Column(name = "first_name", nullable = false, length = 50)
    @Schema(description = "First name of the person", example = "John")
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    @Schema(description = "Last name of the person", example = "Doe")
    private String lastName;

    @Column(name = "birth_date")
    @Schema(description = "Birth date of the person", example = "1990-01-01")
    private Date birthDate;

    @Column(name = "gender", length = 1)
    @Schema(description = "Gender of the person", example = "m")
    private char gender;

    @Column(name = "birth_place", length = 100)
    @Schema(description = "Birth place of the person", example = "New York")
    private String birthPlace;

    @Column(name = "profile_image", length = 255)
    @Schema(description = "Profile image URL of the person", example = "http://example.com/image.jpg")
    private String profileImage;

    @Column(name = "last_checkout", columnDefinition = "TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP")
    @Schema(description = "Last checkout timestamp of the person", example = "2023-01-01T12:00:00Z")
    private Timestamp lastCheckout;

    @ManyToOne
    @JoinColumn(name = "changed_by")
    @Schema(description = "User who last changed the person record", example = "123e4567-e89b-12d3-a456-426614174000")
    private User changedBy;
}