package com.stage.rentalcar.config;

import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Immutable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.Arrays;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final AuthTokenFilter authTokenFilter;

    @Bean
    public RequestContextListener requestContextListener() {
        return new RequestContextListener();
    }

    private static final String[] ADMIN_MATCHER = {
            "/cars/**",
            "/reservations/approve/**",
            "/reservations/decline/**",
            "/users/**",
    };

    private static final String[] CUSTOMER_MATCHER = {
            "/reservations",
            "/reservations/delete/**"
    };

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
        return authenticationManagerBuilder.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "OPTIONS", "DELETE", "PUT", "PATCH"));
        config.setAllowCredentials(true);
        config.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> {
            try {
                requests
                        .requestMatchers("/login").permitAll()
                        .requestMatchers(HttpMethod.GET, "/users/{id}").hasAnyAuthority("ADMIN", "CUSTOMER")
                        .requestMatchers(HttpMethod.GET, "/cars/**").hasAnyAuthority("ADMIN", "CUSTOMER")
                        .requestMatchers(HttpMethod.POST, "/users").hasAnyAuthority("ADMIN", "CUSTOMER")
                        .requestMatchers(HttpMethod.POST, "/reservations").hasAuthority("CUSTOMER")
                        .requestMatchers(HttpMethod.POST, "/cars/free-cars").hasAuthority("CUSTOMER")
                        .requestMatchers(HttpMethod.DELETE, "/reservations").hasAuthority("ADMIN")
                        .requestMatchers(ADMIN_MATCHER).hasAuthority("ADMIN")
                        .requestMatchers(CUSTOMER_MATCHER).hasAuthority("CUSTOMER")
                        .anyRequest().authenticated();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        http.cors().and().csrf().disable().authorizeHttpRequests();
        http.addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
