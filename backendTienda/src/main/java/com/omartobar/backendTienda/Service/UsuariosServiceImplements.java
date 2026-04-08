package com.omartobar.backendTienda.Service;

import com.omartobar.backendTienda.Entity.Usuarios;
import com.omartobar.backendTienda.Repository.UsuariosRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuariosServiceImplements implements UsuariosService {

    private final UsuariosRepository usuariosRepository;

    public UsuariosServiceImplements(UsuariosRepository usuariosRepository) {
        this.usuariosRepository = usuariosRepository;
    }

    @Override
    public List<Usuarios> getAllUsuarios() {
        return usuariosRepository.findAll();
    }

    @Override
    public Usuarios getUsuarioById(Integer id) {
        return usuariosRepository.findById(id).orElse(null);
    }

    @Override
    public Usuarios saveUsuario(Usuarios usuario) throws RuntimeException {
        return usuariosRepository.save(usuario);
    }

    @Override
    public Usuarios updateUsuario(Integer id, Usuarios usuario) {
        Optional<Usuarios> usuarioExistente = usuariosRepository.findById(id);

        if (usuarioExistente.isPresent()) {
            Usuarios newUsuario = usuarioExistente.get();
            newUsuario.setUsername(usuario.getUsername());
            newUsuario.setPasword(usuario.getPasword());
            newUsuario.setEmail(usuario.getEmail());
            newUsuario.setRol(usuario.getRol());
            newUsuario.setEstado(usuario.getEstado());

            return usuariosRepository.save(newUsuario);
        } else {
            return null;
        }
    }

    @Override
    public void deleteUsuario(Integer id) {
        usuariosRepository.deleteById(id);
    }
}