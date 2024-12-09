package io.fam.famapi.service;

import io.fam.famapi.model.Person;
import io.fam.famapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    public Person getPersonById(UUID id) {
        return personRepository.findById(id).orElse(null);
    }

    public Person createPerson(Person person) {
        return personRepository.save(person);
    }

    public Person updatePerson(UUID id, Person personDetails) {
        Person person = personRepository.findById(id).orElse(null);
        if (person != null) {
            person.setFirstName(personDetails.getFirstName());
            person.setLastName(personDetails.getLastName());
            person.setBirthDate(personDetails.getBirthDate());
            person.setGender(personDetails.getGender());
            person.setBirthPlace(personDetails.getBirthPlace());
            person.setProfileImage(personDetails.getProfileImage());
            person.setLastCheckout(personDetails.getLastCheckout());
            person.setChangedBy(personDetails.getChangedBy());
            return personRepository.save(person);
        }
        return null;
    }

    public void deletePerson(UUID id) {
        personRepository.deleteById(id);
    }
}