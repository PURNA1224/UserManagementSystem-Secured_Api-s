package com.userSphere.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.userSphere.repositories.UsersRepo;
import com.userSphere.services.UsersService;

/**
 * SecurityConfig configures the Spring Security framework for the application.
 * It defines password encoding, authentication manager, and security filter chain
 * to handle authorization and authentication of HTTP requests.
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Autowired
    UsersRepo usersRepo;

    @Autowired
    UsersService usersService;

    /**
     * Bean for password encoder using BCrypt hashing algorithm.
     * 
     * @return BCryptPasswordEncoder instance used for encoding passwords.
     */
    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Defines the security filter chain with configuration for:
     * - Disabling CSRF (for stateless APIs)
     * - Permitting all requests (adjust this as needed)
     * - Enabling H2 console access
     * - Adding custom authentication and authorization filters
     * - Stateless session management
     * - Frame options for H2 console compatibility
     * 
     * @param security HttpSecurity object provided by Spring Security
     * @return configured SecurityFilterChain bean
     * @throws Exception if configuration fails
     */
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
        AuthenticationManagerBuilder authenticationBuilder = security.getSharedObject(AuthenticationManagerBuilder.class);
        
        // Configure AuthenticationManager with userDetailsService and password encoder
        authenticationBuilder.userDetailsService(usersService).passwordEncoder(passwordEncoder());

        AuthenticationManager authenticationManager = authenticationBuilder.build();

        return security
                .csrf(csrfConfig -> csrfConfig.disable())  // Disable CSRF protection for stateless APIs
                .authorizeHttpRequests(req -> req
                        .requestMatchers("/**").permitAll()             // Permit all requests - consider tightening this for production
                        .requestMatchers("/h2-console/**").permitAll()  // Allow access to H2 console
                        .anyRequest().authenticated()                    // All other requests require authentication
                )
                .addFilter(new AuthenticationFilter(authenticationManager, usersRepo))  // Add custom authentication filter
                .authenticationManager(authenticationManager)
                .addFilter(new AuthorizationFilter(authenticationManager, usersRepo))   // Add custom authorization filter
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))  // Stateless session management
                .headers(header -> header.frameOptions(frameOptions -> frameOptions.sameOrigin()))  // Allow frames for H2 console
                .build();
    }
}
