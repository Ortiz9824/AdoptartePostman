// Esta línea INDICA que el archivo debe estar en la subcarpeta "Request"
package com.example.proyectoFormativo.Dto.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    // Campo para el login
    private String username;

    // Campos del usuario (de tu versión 1)
    private String firstname;
    private String lastname;
    private String email;
    private String password;

    // Campos adicionales que necesita tu lógica de registro
    // (de tu versión 2)
    private String documento;
    private String direccion;
    private String telefono;
    private Integer generoId;         // <-- Para la relación con Genero
    private Integer tipoDocumentoId;  // <-- Para la relación con TipoDocumento

    // Nota: Las anotaciones @Data, @Builder, etc., de Lombok
    // crean TODOS los Getters, Setters y Constructores por ti.
    // No necesitas escribirlos manualmente.
}