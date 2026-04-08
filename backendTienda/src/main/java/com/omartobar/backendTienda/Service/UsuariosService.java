package com.omartobar.backendTienda.Service;

import com.omartobar.backendTienda.Entity.Usuarios;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UsuariosService {

    List<Usuarios> getAllUsuarios();
    Usuarios getUsuarioById(Integer id);
    Usuarios saveUsuario(Usuarios usuario) throws RuntimeException;
    Usuarios updateUsuario(Integer id, Usuarios usuario);
    void deleteUsuario(Integer id);

}
