package ahiru.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import ahiru.model.User;
import ahiru.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class SecurityConfig {

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	UserDetailsService userDetailsService(UserRepository userRepository) {
		return username -> {
			User user = userRepository.findUserByUsername(username);
			if (user != null) {
				return user;
			}

			throw new UsernameNotFoundException(
					"User '" + username + "' not found");
		};
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http)
			throws Exception {
		return http
				.authorizeHttpRequests(req -> req
						.requestMatchers("/api/**").hasRole("ADMIN")
						.requestMatchers("/timeTable/**")
						.hasAnyRole("ADMIN", "USER")
						.requestMatchers("/", "/**").permitAll())
				.csrf().disable().formLogin(log -> log.loginPage("/login"))
				.build();
	}

}
