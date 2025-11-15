package com.example.proyectoFormativo.Dto.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {

    // El token JWT
    private String token;

    // El tipo de token (normalmente "Bearer")
    private String tokenType;

    // El tiempo de expiración en segundos
    private long expiresInSeconds;

}