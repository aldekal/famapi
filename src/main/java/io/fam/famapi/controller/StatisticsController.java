package io.fam.famapi.controller;

import io.fam.famapi.dto.GenderStatisticsDTO;
import io.fam.famapi.service.StatisticsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for handling statistics-related endpoints.
 */
@RestController
@RequestMapping("/api/statistics")
@Tag(name = "Statistics", description = "API for retrieving statistical data")
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    /**
     * Retrieves gender statistics including total number of persons, males,
     * females, and others.
     *
     * @return A formatted string containing gender statistics.
     */
    @GetMapping("/gender")
    @Operation(summary = "Get gender statistics")
    public ResponseEntity<GenderStatisticsDTO> getGenderStatistics() {
        GenderStatisticsDTO stats = statisticsService.getGenderStatistics();
        return ResponseEntity.ok(stats);
    }
}