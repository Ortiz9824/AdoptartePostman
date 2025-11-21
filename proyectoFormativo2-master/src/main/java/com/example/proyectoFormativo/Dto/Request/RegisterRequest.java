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


    private String nombre;
    private String apellido;
    private String documentoUsuario;
    private String emailUsuario;
    private String password;
    private String direccionUsuario;
    private String telefonoUsuario;
    private Integer generoId;
    private Integer tipoDocumentoId;

}