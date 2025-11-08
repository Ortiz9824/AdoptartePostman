package com.example.proyectoFormativo.Services;

import com.example.proyectoFormativo.Model.Usuario;
// Importa el REPOSITORIO con el nuevo nombre
import com.example.proyectoFormativo.Repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    // Inyecta la interfaz con el nuevo nombre
    @Autowired
    private IUsuarioRepository repository;

    public List<Usuario> getUsuarios() {
        return repository.findAll();
    }

    public Optional<Usuario> getUsuario(Integer id) {
        return repository.findById(id);
    }

    public Usuario saveUsuario(Usuario usuario) {
        return repository.save(usuario);
    }

    public Usuario updateUsuario(Integer id, Usuario usuarioActualizado) {
        return repository.findById(id)
                .map(usuario -> {
                    usuario.setNombreUsuario(usuarioActualizado.getNombreUsuario());
                    usuario.setApellidoUsuario(usuarioActualizado.getApellidoUsuario());
                    // ... actualizar otros campos
                    return repository.save(usuario);
                }).orElse(null);
    }

    public void deleteUsuario(Integer id) {
        repository.deleteById(id);
    }
}