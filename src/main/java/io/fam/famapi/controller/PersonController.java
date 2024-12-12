package io.fam.famapi.controller;

import io.fam.famapi.dto.NextBirthdayDTO;
import io.fam.famapi.dto.PersonDTO;
import io.fam.famapi.model.Person;
import io.fam.famapi.service.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/persons")
@Tag(name = "Person", description = "API for managing persons")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    @Operation(summary = "Get all persons")
    public List<PersonDTO> getAllPersons() {
        // Ruft eine Liste von DTOs aus dem Service ab
        return personService.getAllPersons();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get person by ID")
    public ResponseEntity<PersonDTO> getPersonById(@PathVariable UUID id) {
        Person person = personService.getPersonById(id);
        if (person != null) {
            return ResponseEntity.ok(personService.convertToDTO(person));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Operation(summary = "Create a new person")
    public ResponseEntity<PersonDTO> createPerson(@RequestBody PersonDTO personDTO) {
        PersonDTO createdPerson = personService.createPerson(personDTO);
        return ResponseEntity.ok(createdPerson);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing person")
    public ResponseEntity<PersonDTO> updatePerson(@PathVariable UUID id, @RequestBody PersonDTO personDTO) {
        PersonDTO updatedPerson = personService.updatePerson(id, personDTO);
        if (updatedPerson != null) {
            return ResponseEntity.ok(updatedPerson);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a person")
    public ResponseEntity<Void> deletePerson(@PathVariable UUID id) {
        personService.deletePerson(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/next-birthday")
    @Operation(summary = "Get the next birthday of a person")
    public ResponseEntity<NextBirthdayDTO> getNextBirthday(@PathVariable UUID id) {
        LocalDate nextBirthday = personService.getNextBirthday(id);
        NextBirthdayDTO response = new NextBirthdayDTO(id, nextBirthday);
        return ResponseEntity.ok(response);
    }
}
