package com.exceptional.PlacementManager.config;

import com.exceptional.PlacementManager.jwt.JwtFilter;
import com.exceptional.PlacementManager.jwt.JwtUtils;
import com.exceptional.PlacementManager.service.MyUserDetailsService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtFilter jwtFilter;
    private final MyUserDetailsService userDetailsService;

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return http
//                .cors(corsConfigurer -> corsConfigurer
//                        .configurationSource(request -> {
//                            var cors = new org.springframework.web.cors.CorsConfiguration();
//                            cors.setAllowedOrigins(List.of(
//                                    "http://localhost:8082",
//                                    "http://localhost:8080"
//                            )); // Allow specific origins
//                            cors.setAllowedMethods(List.of(
//                                    "GET", "POST", "PUT", "DELETE", "OPTIONS"
//                            )); // Allow HTTP methods
//                            cors.setAllowedHeaders(List.of("*")); // Allow all headers
//                            cors.setAllowCredentials(true); // Allow credentials for cross-origin requests
//                            return cors;
//                        })
//                )
//                .csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(request -> request
//                        .requestMatchers(
//                                "/login",
//                                "/register",
//                                "/signin",
//                                "/register-verify-otp",
//                                "/job-offers"
//                        ).permitAll() // Public endpoints
//                        .anyRequest().authenticated() // Secure all other endpoints
//                )
//                .httpBasic(Customizer.withDefaults())
//                .sessionManagement(session -> session
//                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                )
//                .logout(logout -> logout
//                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // Specify logout endpoint
//                        .logoutSuccessHandler((request, response, authentication) -> {
//                            response.setStatus(HttpServletResponse.SC_OK);
//                        })
//                        .invalidateHttpSession(true) // Invalidate the session
//                        .deleteCookies("JSESSIONID") // Delete session cookie
//                )
//
//                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
//
//                .build();
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors(Customizer.withDefaults()) // Use the CORS configuration from WebConfig
                .csrf(AbstractHttpConfigurer::disable) // Disable CSRF for APIs
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/register", "/signin", "/register-verify-otp", "/job-offers").permitAll()
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }


    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();

    }

}
