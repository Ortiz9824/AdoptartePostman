package com.example.proyectoFormativo.Interface;

// --- IMPORTS CORREGIDOS ---
// Apuntando a las carpetas Request y Response
import com.example.proyectoFormativo.Dto.Request.LoginRequest;
import com.example.proyectoFormativo.Dto.Request.RegisterRequest;
import com.example.proyectoFormativo.Dto.Response.AuthResponse;
import com.example.proyectoFormativo.Dto.Response.JwtResponse; // Asumiendo que JwtResponse va aquí

public interface IAuthService {

    AuthResponse<String> register(RegisterRequest req);

    // --- MÉTODO CORREGIDO ---
    // Simplemente usa 'LoginRequest' (de la importación)
    AuthResponse<JwtResponse> login(LoginRequest req);
}