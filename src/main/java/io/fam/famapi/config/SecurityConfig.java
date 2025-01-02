package io.fam.famapi.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
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
				.anyRequest().authenticated()
			)
			.httpBasic(Customizer.withDefaults())
			.formLogin(Customizer.withDefaults());

		return http.build();
	}


	@Bean
	UserDetailsManager users(DataSource dataSource) {
		UserDetails user = User.builder()
			.username("user")
			.password("{bcrypt}$2a$10$AiyMWI4UBLozgXq6itzyVuxrtofjcPzn/WS3fOrcqgzdax9jB7Io.")
			.roles("USER")
			.build();
		UserDetails admin = User.builder()
			.username("admin")
			.password("{bcrypt}$2a$10$AiyMWI4UBLozgXq6itzyVuxrtofjcPzn/WS3fOrcqgzdax9jB7Io.")
			.roles("USER", "ADMIN")
			.build();
		JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
		users.createUser(user);
		users.createUser(admin);
		return users;
	}
	
}
