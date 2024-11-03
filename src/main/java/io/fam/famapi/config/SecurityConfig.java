package io.fam.famapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests()
            .requestMatchers("/public/**").permitAll() // public endpoints
            .requestMatchers("/api/admin/**").hasRole("ADMIN") // accessible only for admins
            .requestMatchers("/api/user/**").hasAnyRole("USER", "ADMIN") // accessible for users and admins
            .anyRequest().authenticated()
            .and()
            .httpBasic(); // or .formLogin() for a login form

        return http.build();
    }
}

