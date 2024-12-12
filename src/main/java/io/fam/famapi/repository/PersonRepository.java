package io.fam.famapi.repository;

import io.fam.famapi.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.UUID;

/**
 * Repository interface for managing {@link Person} entities.
 * Provides methods for performing CRUD operations and custom queries.
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, UUID> {

    /**
     * Counts the number of persons with the specified gender.
     *
     * @param gender the gender character ('m' for male, 'f' for female, 'o' for other)
     * @return the number of persons matching the gender
     */
    int countByGender(char gender);

    /**
     * Counts the number of persons from a specific birth place.
     *
     * @param birthPlace the birth place to filter by
     * @return the number of persons born in the specified place
     */
    int countByBirthPlace(String birthPlace);

    /**
     * Counts the number of persons born on a specific date.
     *
     * @param birthDate the birth date to filter by (format: "YYYY-MM-DD")
     * @return the number of persons born on the specified date
     */
    int countByBirthDate(Timestamp birthDate);

}