package io.fam.famapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.fam.famapi.dto.UserRegistrationDTO;
import io.fam.famapi.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
/**
 * Controller for handling authentication requests
 */
@RestController
@RequestMapping("/api/auth")
@Tag(name = "Auth", description = "Auth management APIs")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    @Operation(summary = "register a new user", description = "Register a new user with the provided details")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "User registered successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input data e.g. Username already exists"),
    })
    public ResponseEntity<String> signup(@Valid @RequestBody UserRegistrationDTO user) {
        authService.signup(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    }

    
}
