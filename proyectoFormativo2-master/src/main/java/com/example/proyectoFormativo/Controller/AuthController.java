package com.example.proyectoFormativo.Controller;

import com.example.proyectoFormativo.Dto.Request.LoginRequest;
import com.example.proyectoFormativo.Dto.Request.RegisterRequest;
import com.example.proyectoFormativo.Dto.Response.AuthResponse;
import com.example.proyectoFormativo.Interface.IAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth") // URL base para autenticación
@RequiredArgsConstructor
public class AuthController {

    private final IAuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}