package com.example.proyectoFormativo.Services;

// 1. IMPORTS CORREGIDOS
import com.example.proyectoFormativo.Dto.Request.LoginRequest;
import com.example.proyectoFormativo.Dto.Request.RegisterRequest;
import com.example.proyectoFormativo.Dto.Response.AuthResponse;
import com.example.proyectoFormativo.Dto.Response.JwtResponse; // Asumiendo que JwtResponse va en Response
import com.example.proyectoFormativo.Interface.IAuthService;
import com.example.proyectoFormativo.Model.Genero; // Necesario para los IDs
import com.example.proyectoFormativo.Model.Rol;
import com.example.proyectoFormativo.Model.TipoDocumento; // Necesario para los IDs
import com.example.proyectoFormativo.Model.User; // Tu entidad de Usuario
import com.example.proyectoFormativo.Repository.IGeneroRepository; // Repositorio necesario
import com.example.proyectoFormativo.Repository.IRolRepository;
import com.example.proyectoFormativo.Repository.ITipoDocumentoRepository; // Repositorio necesario
import com.example.proyectoFormativo.Repository.IUserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails; // Importante
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthService implements IAuthService {

    private final AuthenticationManager authManager;
    private final UserDetailsService uds;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final IUserRepository userRepository;
    private final IRolRepository roleRepository;

    // Repositorios ADICIONALES que necesitamos para un registro completo
    private final IGeneroRepository generoRepository;
    private final ITipoDocumentoRepository tipoDocumentoRepository;

    // Constructor actualizado para incluir los nuevos repositorios
    public AuthService(AuthenticationManager authManager, UserDetailsService uds, JwtService jwtService, PasswordEncoder passwordEncoder, IUserRepository userRepository, IRolRepository roleRepository, IGeneroRepository generoRepository, ITipoDocumentoRepository tipoDocumentoRepository) {
        this.authManager = authManager;
        this.uds = uds;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.generoRepository = generoRepository;
        this.tipoDocumentoRepository = tipoDocumentoRepository;
    }


    @Override
    @Transactional
    public AuthResponse<String> register(RegisterRequest req) {
        // Usaremos el AuthResponse genérico que ya tenías
        AuthResponse<String> response = new AuthResponse<>();
        try{
            // 2. SINTAXIS CORREGIDA (con getters)
            if (userRepository.existsByUsername(req.getUsername())) {
                response.setHttpStatusCode(HttpStatus.CONFLICT.value());
                response.setMessage("El usuario (username) ya existe");
                response.setData(null);
                return response;
            }

            if (userRepository.existsByEmail(req.getEmail())) {
                response.setHttpStatusCode(HttpStatus.CONFLICT.value());
                response.setMessage("El email ya está registrado");
                response.setData(null);
                return response;
            }

            // 3. LÓGICA DE CAMPOS COMPLETOS
            User user = new User();
            user.setUsername(req.getUsername());
            user.setPassword(passwordEncoder.encode(req.getPassword())); // Contraseña encriptada
            user.setEmail(req.getEmail());
            user.setFirstname(req.getFirstname());
            user.setLastname(req.getLastname());
            user.setDocumento(req.getDocumento());
            user.setDireccion(req.getDireccion());
            user.setTelefono(req.getTelefono());
            user.setActive(true); // El usuario nace activo

            // 4. MANEJO DE RELACIONES (IDs)
            Genero genero = generoRepository.findById(req.getGeneroId())
                    .orElseThrow(() -> new RuntimeException("Error: Genero no encontrado."));
            user.setGenero(genero);

            TipoDocumento tipoDoc = tipoDocumentoRepository.findById(req.getTipoDocumentoId())
                    .orElseThrow(() -> new RuntimeException("Error: Tipo de Documento no encontrado."));
            user.setTipoDocumento(tipoDoc);

            // 5. LÓGICA DE ROL CORREGIDA
            // Un usuario nuevo debe ser CLIENTE, no VETERINARIO
            Rol userRole = roleRepository.findByName("CLIENTE") // O "USUARIO", como lo llames
                    .orElseThrow(() -> new RuntimeException("Error: Rol base no existe (CLIENTE)."));

            Set<Rol> roles = new HashSet<>();
            roles.add(userRole);
            user.setRoles(roles);

            // Guardar el usuario completo
            userRepository.save(user);

            response.setHttpStatusCode(HttpStatus.CREATED.value());
            response.setMessage("Registro exitoso");
            response.setData("Usuario creado correctamente");
            return response;

        } catch (Exception ex) {
            // Captura cualquier error (ej. Rol no encontrado, Genero no encontrado)
            response.setHttpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setMessage("Ocurrió un error al registrar el usuario: " + ex.getMessage());
            return response;
        }
    }

    @Override
    public AuthResponse<JwtResponse> login(LoginRequest req) {
        AuthResponse<JwtResponse> response = new AuthResponse<>();

        try {
            // 6. SINTAXIS CORREGIDA (con getters)
            authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword())
            );

            // El 'uds' (UserDetailsService) es correcto
            var user = (UserDetails) uds.loadUserByUsername(req.getUsername());
            var token = jwtService.generateToken(user.getUsername(), user.getAuthorities());

            // Asumiendo que JwtResponse es un DTO en Dto/Response
            var payload = new JwtResponse(token, "Bearer", jwtService.getExpiresInSeconds());

            response.setHttpStatusCode(HttpStatus.OK.value());
            response.setMessage("Inicio de sesión exitoso");
            response.setData(payload);
            return response;

        } catch (Exception ex) {
            response.setHttpStatusCode(HttpStatus.UNAUTHORIZED.value());
            response.setMessage("Credenciales inválidas: " + ex.getMessage());
            return response;
        }
    }
}