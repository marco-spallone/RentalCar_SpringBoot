package com.stage.rentalcar.config;

import com.stage.rentalcar.filter.AuthTokenFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.client.OAuth2LoginConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.context.request.RequestContextListener;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final AuthTokenFilter authTokenFilter;

    @Bean
    public RequestContextListener requestContextListener(){
        return new RequestContextListener();
    }

    private static final String[] ADMIN_MATCHER={
            "/cars/post-car",
            "/cars/delete/**",
            "/reservations/approve/**",
            "/reservations/decline/**",
            "/users",
            "/users/delete/**"
    };

    private static final String[] CUSTOMER_MATCHER={
            "/cars/free-cars",
            "/reservations/post-reservation",
            "/reservations/delete/**",
            "/deleteReservation/**"
    };
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
        return authenticationManagerBuilder.build();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> {
            try {
                requests
                        .requestMatchers("/login").permitAll()
                        .requestMatchers("/users/post-user").hasAnyAuthority("ADMIN", "CUSTOMER")
                        .requestMatchers("/customers/userProfile").hasAnyAuthority("ADMIN", "CUSTOMER")
                        .requestMatchers(ADMIN_MATCHER).hasAuthority("ADMIN")
                        .requestMatchers(CUSTOMER_MATCHER).hasAuthority("CUSTOMER")
                        .anyRequest().authenticated()
                        .and().csrf().disable();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        http.addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
