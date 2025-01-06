package io.fam.famapi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import io.fam.famapi.dto.UserRegistrationDTO;
import io.fam.famapi.exception.InvalidValueException;
import io.fam.famapi.exception.LengthException;
import io.fam.famapi.exception.NullValueException;
import io.fam.famapi.exception.ValueAlreadyExistsException;
import io.fam.famapi.exception.ValuesNotMatchException;

@Service
public class AuthService {

    private UserDetailsManager userDetailsManager;
    private PasswordEncoder passwordEncoder;

    private final static int min = 5;
    private final static int max = 20;
    private final static String DEFAULT_ROLE = "USER";

    public AuthService(UserDetailsManager userDetailsManager, PasswordEncoder passwordEncoder) {
        this.userDetailsManager = userDetailsManager;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Signup a new user
     * @param registrationDto
     * @throws NullValueException if the registration details are null
     * @throws ValueAlreadyExistsException if the username already exists
     * @throws InvalidValueException if the username contains white spaces
     * @throws ValuesNotMatchException if the password and password confirmation do not match
     * @throws LengthException if the username or password length is less than min or greater than max
     * @throws LengthException if the password length is less than min or greater than max
     */
    public void signup(final UserRegistrationDTO registrationDto) {
        if (registrationDto == null) {
            throw new NullValueException("registration details");
        }
        // handle NullpointerException if the username is null
        if (registrationDto.getUsername() == null) {
            throw new NullValueException("username");
        }
        // convert the username to lowercase to avoid case sensitivity.
        registrationDto.setUsername(registrationDto.getUsername().toLowerCase());
        signupValidation(registrationDto);
        UserDetails user = User.builder()
                .username(registrationDto.getUsername().toLowerCase())
                .password(passwordEncoder.encode(registrationDto.getPassword())) // Encode password using BCrypt
                .roles(DEFAULT_ROLE)
                .build();
        userDetailsManager.createUser(user);
    }

    private void signupValidation(final UserRegistrationDTO registrationDto) {
        if (userDetailsManager.userExists(registrationDto.getUsername())) {
            throw new ValueAlreadyExistsException("username [" + registrationDto.getUsername() + "]");
        }
        if (registrationDto.getUsername() == null) {
            throw new NullValueException("username");
        }
        if (registrationDto.getPassword() == null) {
            throw new NullValueException("password");
        }
        if (!registrationDto.getPassword().equals(registrationDto.getPasswordConfirmation())) {
            throw new ValuesNotMatchException("password", "password confirmation");
        }
        if (!registrationDto.getUsername().trim().equals(registrationDto.getUsername())) {
            throw new InvalidValueException("Username contains white spaces, which");
        }
        if (!registrationDto.getPassword().trim().equals(registrationDto.getPassword()) ||
        !registrationDto.getPasswordConfirmation().trim().equals(registrationDto.getPasswordConfirmation())) {
            throw new InvalidValueException("password contains white spaces, which");
        }
        if (registrationDto.getPasswordConfirmation() == null) {
            throw new NullValueException("password confirmation");
        }
        if (registrationDto.getPassword().length() < min
                || registrationDto.getPassword().length() > max) {
            throw new LengthException("password", min, max);
        }
        if (registrationDto.getUsername().length() < min
                || registrationDto.getUsername().length() > max) {
            throw new LengthException("username", min, max);
        }
        
    }
}
