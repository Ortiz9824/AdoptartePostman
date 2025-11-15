package com.example.proyectoFormativo.Dto.Response;

import lombok.Data;

@Data
public class MascotaResponseDto {

    private Integer id;
    private String nombre;
    private String raza;
    private String tamano; // Enviamos el nombre, no el ID
    private String dueno;  // Enviamos el username del dueño, no el objeto

    // Añade otros campos que quieras MOSTRAR al cliente
}