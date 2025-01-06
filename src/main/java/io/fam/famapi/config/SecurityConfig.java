package io.fam.famapi.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((authorize) -> authorize
                .requestMatchers("/api/auth/signup").permitAll() // Allow unauthenticated access to the signup endpoint
                .anyRequest().authenticated()         // Any other request must be authenticated
            )
			.csrf((csrf) -> csrf.ignoringRequestMatchers("/api/auth/signup")) // Disable CSRF protection for the signup endpoint
            .httpBasic(Customizer.withDefaults())      // Authenticate users with HTTP basic authentication
            .formLogin(Customizer.withDefaults());     // Enable form based login

        return http.build();
    }


	@Bean
	UserDetailsManager users(DataSource dataSource) {
		return new JdbcUserDetailsManager(dataSource);
	}

	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
}
