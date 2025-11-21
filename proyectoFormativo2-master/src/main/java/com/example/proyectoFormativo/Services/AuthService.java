package com.example.proyectoFormativo.Services;

import com.example.proyectoFormativo.Dto.Request.LoginRequest;
import com.example.proyectoFormativo.Dto.Request.RegisterRequest;
import com.example.proyectoFormativo.Dto.Response.AuthResponse;
import com.example.proyectoFormativo.Interface.IAuthService;
import com.example.proyectoFormativo.Model.Genero;
import com.example.proyectoFormativo.Model.Rol;
import com.example.proyectoFormativo.Model.TipoDocumento;
import com.example.proyectoFormativo.Model.Usuario;
import com.example.proyectoFormativo.Repository.IGeneroRepository;
import com.example.proyectoFormativo.Repository.IRolRepository;
import com.example.proyectoFormativo.Repository.ITipoDocumentoRepository;
import com.example.proyectoFormativo.Repository.IUsuarioRepository;
import lombok.RequiredArgsConstructor; // <-- Usa Lombok
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class AuthService implements IAuthService {

    private final IUsuarioRepository usuarioRepository;
    private final IRolRepository rolRepository;
    private final IGeneroRepository generoRepository;
    private final ITipoDocumentoRepository tipoDocumentoRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    @Override
    public AuthResponse register(RegisterRequest request) {


        if (usuarioRepository.existsByEmailUsuario(request.getEmailUsuario())) {
            throw new RuntimeException("El email ya está registrado");
        }

        Genero genero = generoRepository.findById(request.getGeneroId())
                .orElseThrow(() -> new RuntimeException("Error: Genero no encontrado."));

        TipoDocumento tipoDoc = tipoDocumentoRepository.findById(request.getTipoDocumentoId())
                .orElseThrow(() -> new RuntimeException("Error: Tipo de Documento no encontrado."));

        Rol rolPorDefecto = rolRepository.findByNombreRol("CLIENTE")
                .orElseThrow(() -> new RuntimeException("Error: Rol CLIENTE no encontrado."));


        Usuario usuario = new Usuario();
        usuario.setNombre(request.getNombre()); // <-- Campo correcto
        usuario.setApellido(request.getApellido()); // <-- Campo correcto
        usuario.setEmailUsuario(request.getEmailUsuario()); // <-- Campo correcto
        usuario.setPasswordUsuario(passwordEncoder.encode(request.getPassword())); // <-- Campo correcto
        usuario.setDocumentoUsuario(request.getDocumentoUsuario());
        usuario.setDireccionUsuario(request.getDireccionUsuario());
        usuario.setTelefonoUsuario(request.getTelefonoUsuario());


        usuario.setGenero(genero);
        usuario.setTipoDocumento(tipoDoc);
        usuario.setRol(rolPorDefecto); // <-- Asignación SINGULAR


        usuarioRepository.save(usuario);


        String jwtToken = jwtService.generateToken(usuario);

        return AuthResponse.builder().token(jwtToken).build();
    }


    @Override
    public AuthResponse login(LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(), // Usa email
                        request.getPassword()
                )
        );

        Usuario usuario = usuarioRepository.findByEmailUsuario(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));


        String jwtToken = jwtService.generateToken(usuario);

        return AuthResponse.builder().token(jwtToken).build();
    }
}