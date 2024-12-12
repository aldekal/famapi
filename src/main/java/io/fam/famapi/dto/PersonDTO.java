package io.fam.famapi.dto;

import lombok.Data;
import lombok.Builder;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.util.UUID;

/**
 * Data Transfer Object for Person without ID and lastCheckout.
 */
@Data
@Builder
@Schema(description = "Person DTO representing a person in the system")
public class PersonDTO {

    @Schema(description = "ID of the person", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID id;

    @Schema(description = "First name of the person", example = "John")
    private String firstName;

    @Schema(description = "Last name of the person", example = "Doe")
    private String lastName;

    @Schema(description = "Birth date of the person", example = "1990-01-01")
    private LocalDate birthDate;

    @Schema(description = "Gender of the person", example = "m")
    private char gender;

    @Schema(description = "Birth place of the person", example = "New York")
    private String birthPlace;

    @Schema(description = "Profile image URL of the person", example = "http://example.com/image.jpg")
    private String profileImage;
}
