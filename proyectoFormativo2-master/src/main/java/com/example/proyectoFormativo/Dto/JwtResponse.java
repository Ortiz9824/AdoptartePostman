package com.example.proyectoFormativo.Dto;

public record JwtResponse(String access_token, String token_type, long expires_in) {}
