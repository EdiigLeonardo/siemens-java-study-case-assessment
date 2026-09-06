package com.siemens.leaveportal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(c -> c.disable())
            .authorizeHttpRequests(a -> a
                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                .anyRequest().authenticated())
            .httpBasic(b -> {});
        return http.build();
    }

    // BUG: authority definida como "MANAGER", sem o prefixo "ROLE_" que
    // hasRole('MANAGER') exige por convencao do Spring Security.
    @Bean
    public UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager(
                User.withDefaultPasswordEncoder().username("ana").password("pw").authorities("MANAGER").build(),
                User.withDefaultPasswordEncoder().username("joao").password("pw").authorities("EMPLOYEE").build()
        );
    }
}
