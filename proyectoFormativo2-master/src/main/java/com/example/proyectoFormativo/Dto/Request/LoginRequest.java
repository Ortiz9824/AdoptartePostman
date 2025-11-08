package com.example.proyectoFormativo.Dto.Request; // <-- El paquete debe ser .Dto.Request

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    String email;
    String password;
}