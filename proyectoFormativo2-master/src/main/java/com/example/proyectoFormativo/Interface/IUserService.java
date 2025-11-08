package com.example.proyectoFormativo.Interface;

import com.example.proyectoFormativo.Dto.RegisterRequest;
import com.example.proyectoFormativo.Dto.Response.AuthResponse;
import com.example.proyectoFormativo.Model.User;

public interface IUserService {
    AuthResponse<String> register(RegisterRequest req);
}
