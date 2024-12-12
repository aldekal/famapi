package io.fam.famapi.service;

import io.fam.famapi.model.Person;
import io.fam.famapi.dto.PersonDTO;
import io.fam.famapi.exception.BirthDateNullException;
import io.fam.famapi.exception.PersonNotFoundException;
import io.fam.famapi.repository.PersonRepository;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * Service class for managing Person entities.
 */
@Service
public class PersonService {

    private static final Logger logger = LoggerFactory.getLogger(PersonService.class);

    private final PersonRepository personRepository;

    /**
     * Constructs a new PersonService with the specified PersonRepository.
     *
     * @param personRepository the repository used for Person data access
     */
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    /**
     * Retrieves all persons from the database and converts them to DTOs.
     *
     * @return a list of PersonDTO objects
     */
    public List<PersonDTO> getAllPersons() {
        logger.info("Fetching all persons from the database");
        List<Person> persons = personRepository.findAll();
        logger.debug("Found {} persons", persons.size());
        return persons.stream()
                .map(this::convertToDTO)
                .toList();
    }

    /**
     * Retrieves a person by their unique identifier.
     *
     * @param id the UUID of the person
     * @return the Person object if found, otherwise null
     */
    public Person getPersonById(UUID id) {
        logger.info("Fetching person with ID: {}", id);
        Person person = personRepository.findById(id).orElse(null);
        if (person == null) {
            throw new PersonNotFoundException(id);
        }
        return person;
    }

    /**
     * Creates a new person in the database.
     *
     * @param personDTO the data transfer object containing person information
     * @return the created PersonDTO
     */
    public PersonDTO createPerson(PersonDTO personDTO) {
        logger.info("Creating new person: {} {}", personDTO.getFirstName(), personDTO.getLastName());
        Person person = convertToEntity(personDTO);
        Person savedPerson = personRepository.save(person);
        logger.debug("Person created with ID: {}", savedPerson.getId());
        return convertToDTO(savedPerson);
    }

    /**
     * Updates an existing person in the database.
     *
     * @param id        the UUID of the person to update
     * @param personDTO the data transfer object containing updated person
     *                  information
     * @return the updated PersonDTO if the person exists, otherwise null
     */
    public PersonDTO updatePerson(UUID id, PersonDTO personDTO) {
        logger.info("Updating person with ID: {}", id);
        Person person = personRepository.findById(id).orElse(null);
        if (person != null) {
            person.setFirstName(personDTO.getFirstName());
            person.setLastName(personDTO.getLastName());
            person.setBirthDate(personDTO.getBirthDate());
            person.setGender(personDTO.getGender());
            person.setBirthPlace(personDTO.getBirthPlace());
            person.setProfileImage(personDTO.getProfileImage());
            Person updatedPerson = personRepository.save(person);
            logger.debug("Person updated: {} {}", updatedPerson.getFirstName(), updatedPerson.getLastName());
            return convertToDTO(updatedPerson);
        } else {
            logger.warn("Person with ID: {} not found", id);
            return null;
        }
    }

    /**
     * Deletes a person from the database.
     *
     * @param id the UUID of the person to delete
     */
    public void deletePerson(UUID id) {
        logger.info("Deleting person with ID: {}", id);
        personRepository.deleteById(id);
        logger.debug("Person with ID: {} deleted", id);
    }

    public LocalDate getNextBirthday(UUID id) {
        Person person = getPersonById(id);

        if (person.getBirthDate() == null) {
            throw new BirthDateNullException(id);
        }

        // Berechnung des nächsten Geburtstags
        return calculateNextBirthday(person.getBirthDate());
    }

    private LocalDate calculateNextBirthday(LocalDate birthDate) {
        LocalDate today = LocalDate.now();
        LocalDate nextBirthday = birthDate.withYear(today.getYear());

        if (nextBirthday.isBefore(today) || nextBirthday.equals(today)) {
            nextBirthday = nextBirthday.plusYears(1);
        }

        return nextBirthday;
    }

    /**
     * Converts a Person entity to a PersonDTO.
     *
     * @param person the Person entity to convert
     * @return the corresponding PersonDTO
     */
    public PersonDTO convertToDTO(Person person) {
        return PersonDTO.builder()
                .id(person.getId())
                .firstName(person.getFirstName())
                .lastName(person.getLastName())
                .birthDate(person.getBirthDate())
                .gender(person.getGender())
                .birthPlace(person.getBirthPlace())
                .profileImage(person.getProfileImage())
                .build();
    }

    /**
     * Converts a PersonDTO to a Person entity.
     *
     * @param personDTO the PersonDTO to convert
     * @return the corresponding Person entity
     */
    private Person convertToEntity(PersonDTO personDTO) {
        return Person.builder()
                .firstName(personDTO.getFirstName())
                .lastName(personDTO.getLastName())
                .birthDate(personDTO.getBirthDate())
                .gender(personDTO.getGender())
                .birthPlace(personDTO.getBirthPlace())
                .profileImage(personDTO.getProfileImage())
                .build();
    }
}