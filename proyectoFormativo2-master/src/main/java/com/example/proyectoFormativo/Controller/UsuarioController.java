package com.example.proyectoFormativo.Controller;

import com.example.proyectoFormativo.Model.Usuario;
import com.example.proyectoFormativo.Services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // GET: Obtener todos los usuarios
    @GetMapping
    public List<Usuario> getAllUsuarios() {
        return usuarioService.getUsuarios();
    }

    // GET: Obtener un usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Integer id) {
        return usuarioService.getUsuario(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST: Crear un nuevo usuario
    @PostMapping
    public Usuario createUsuario(@RequestBody Usuario usuario) {
        // La lógica de encriptación de contraseña debería ir en el UsuarioService
        return usuarioService.saveUsuario(usuario);
    }

    // PUT: Actualizar un usuario existente
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable Integer id, @RequestBody Usuario usuarioDetails) {
        Usuario updatedUsuario = usuarioService.updateUsuario(id, usuarioDetails);
        if (updatedUsuario == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedUsuario);
    }

    // DELETE: Borrar un usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Integer id) {
        // Primero verifica si existe antes de borrar para evitar errores
        if (usuarioService.getUsuario(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        usuarioService.deleteUsuario(id);
        return ResponseEntity.ok().build();
    }
}