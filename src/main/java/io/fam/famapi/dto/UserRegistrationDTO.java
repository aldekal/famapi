package io.fam.famapi.dto;

import lombok.Data;
import lombok.Builder;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

/**
 * Data Transfer Object for Registration of a new user.
 */
@Data
@Builder
@Schema(description = "User Registration DTO representing a new user registration")
public class UserRegistrationDTO {

    @Schema(description = "Username of the user", example = "john")
    @NotBlank(message = "Username is mandatory")
    private String username;

    @Schema(description = "Password of the user", example = "password123")
    @NotBlank(message = "Password is mandatory")
    private String password;

    @Schema(description = "Password confirmation of the user", example = "password123")
    @NotBlank(message = "Password confirmation is mandatory")
    private String passwordConfirmation;

}
