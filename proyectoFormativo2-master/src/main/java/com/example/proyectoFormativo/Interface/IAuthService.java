package com.example.proyectoFormativo.Interface;

import com.example.proyectoFormativo.Dto.JwtResponse;
import com.example.proyectoFormativo.Dto.LoginRequest;
import com.example.proyectoFormativo.Dto.RegisterRequest;
import com.example.proyectoFormativo.Dto.Response.AuthResponse;

public interface IAuthService {
    AuthResponse<String> register(RegisterRequest req);
    AuthResponse<JwtResponse> login(LoginRequest req);
}
