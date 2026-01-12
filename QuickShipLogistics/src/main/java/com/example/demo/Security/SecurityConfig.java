package com.example.demo.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// Implemented URL - Based Security over here.
@Configuration
public class SecurityConfig {

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(JwtHelper jwtHelper,
                                                           UserDetailsService userDetailsService) {
        return new JwtAuthenticationFilter(jwtHelper, userDetailsService);
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthenticationFilter jwtAuthenticationFilter) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/login").permitAll()
                        .requestMatchers("/swagger-ui/**","/v3/api-docs/**").permitAll()

                        // RBAC examples
                        .requestMatchers(HttpMethod.POST, "/api/v1/warehouse/packages").hasRole("MANAGER")
                        .requestMatchers("/api/v1/warehouse/analytics/revenue").hasAnyRole("MANAGER","DRIVER")


                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails admin = User.builder()
                .username("Sanidhya")
                .password(passwordEncoder.encode("abc"))
                .roles("MANAGER")
                .build();

        UserDetails basic = User.builder()
                .username("Devesh")
                .password(passwordEncoder.encode("abc"))
                .roles("DRIVER")
                .build();

        return new InMemoryUserDetailsManager(admin, basic);
    }
}

