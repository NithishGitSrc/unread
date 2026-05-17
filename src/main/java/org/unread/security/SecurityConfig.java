package org.unread.security;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter; // 2. Inject your filter

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) {

        return
                http.
                        authorizeHttpRequests(auth -> auth
                                .requestMatchers("/api/auth/**", "/h2-console/**")
                                .permitAll()
                                .anyRequest()
                                .authenticated())
                        .csrf(csrf -> csrf.disable())
                        .sessionManagement(session -> session
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                        .addFilterBefore( jwtAuthFilter , UsernamePasswordAuthenticationFilter.class)
                        .formLogin(form -> form.disable())
                        .httpBasic(basic -> basic.disable())
                        .headers(headers -> headers
                                .frameOptions(frame -> frame.sameOrigin())  // Allow frames from same origin for H2 Console
                        ).build();
    }


}
