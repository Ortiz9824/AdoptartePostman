package com.example.proyectoFormativo.Dto.Request; // <-- Asegúrate que el paquete sea este

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    // Campos que ya tenías
    String firstname;
    String lastname;
    String email;
    String password;

    // --- Campos NUEVOS que faltaban ---
    // (Necesarios para la entidad Usuario y AuthService)
    String documento;
    String direccion;
    String telefono;
    Integer generoId;         // <-- Para la relación con Genero
    Integer tipoDocumentoId;  // <-- Para la relación con TipoDocumento
}