package com.example.proyectoFormativo.Config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter; // Tu filtro JWT
    private final AuthManagerConfig authenticationProvider; // Tu proveedor de autenticación

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable()) // Deshabilitar CSRF para APIs REST
                .authorizeHttpRequests(auth -> auth
                        // Permite el acceso público a los endpoints de registro y login
                        .requestMatchers("/api/auth/**").permitAll()

                        // Define permisos basados en roles
                        // (Ejemplo: Solo 'Administrador' puede ver /api/usuarios)
                        .requestMatchers("/api/usuarios/**").hasAuthority("Administrador")
                        .requestMatchers("/api/mascotas/**").hasAnyAuthority("Administrador", "Administrador Refugio")

                        // Cualquier otra petición debe estar autenticada
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Sesiones sin estado
                )
                .authenticationProvider(authenticationProvider.authenticationProvider()) // Usa tu AuthManagerConfig
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class) // Añade tu filtro JWT
                .build();
    }
}