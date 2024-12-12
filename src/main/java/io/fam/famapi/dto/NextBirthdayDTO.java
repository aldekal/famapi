package io.fam.famapi.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;
import java.util.UUID;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Data Transfer Object for Next Bithday of a {@link Person}.
 */
@Data
@Builder
@Schema(description = "Person DTO representing a person in the system")
public class NextBirthdayDTO {
    private UUID personId;
    private LocalDate nextBirthday;

    public NextBirthdayDTO(UUID personId, LocalDate nextBirthday) {
        this.personId = personId;
        this.nextBirthday = nextBirthday;
    }

}
