// En tu archivo de configuración de Seguridad (ej. SecurityConfig.java)
package com.example.proyectoFormativo.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Asumo que ya tienes inyectados tu JwtAuthenticationFilter y AuthenticationProvider
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    // ... (Tu constructor)

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth

                        // 1. ENDPOINTS PÚBLICOS (Login y Registro)
                        .requestMatchers("/api/v1/auth/**").permitAll() // Todos pueden loguearse/registrarse

                        // 2. ENDPOINTS PARA CLIENTES (Dueños de mascotas)
                        // Un CLIENTE puede ver sus mascotas y crear mascotas
                        .requestMatchers(HttpMethod.POST, "/api/v1/mascotas").hasRole("CLIENTE")
                        .requestMatchers(HttpMethod.GET, "/api/v1/mascotas/**").hasRole("CLIENTE")

                        // 3. ENDPOINTS SOLO PARA VETERINARIO (¡Tu requisito!)
                        // Solo un VETERINARIO puede ver/editar historias y consultas
                        .requestMatchers("/api/v1/historias-medicas/**").hasRole("VETERINARIO")
                        .requestMatchers("/api/v1/consultas-medicas/**").hasRole("VETERINARIO")

                        // 4. CUALQUIER OTRA PETICIÓN debe estar autenticada
                        .anyRequest().authenticated()
                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}