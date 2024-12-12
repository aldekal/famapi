package io.fam.famapi.service;

import org.springframework.stereotype.Service;
import io.fam.famapi.dto.GenderStatisticsDTO;
import io.fam.famapi.repository.PersonRepository;

/**
 * Service class for retrieving statistical data related to persons.
 */
@Service
public class StatisticsService {

    private final PersonRepository personRepository;

    /**
     * Constructs a new {@code StatisticsService} with the given {@code PersonRepository}.
     *
     * @param personRepository the repository used to access person data
     */
    public StatisticsService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    /**
     * Retrieves gender statistics including total number of persons,
     * and counts of males, females, and others.
     *
     * @return a {@link GenderStatisticsDTO} containing the gender statistics
     */
    public GenderStatisticsDTO getGenderStatistics() {
        return GenderStatisticsDTO.builder()
                .totalPersons(personRepository.count())
                .maleCount(personRepository.countByGender('m'))
                .femaleCount(personRepository.countByGender('f'))
                .otherCount(personRepository.countByGender('o'))
                .build();
    }

}
