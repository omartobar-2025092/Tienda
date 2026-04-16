package com.omartobar.backendTienda.Controller;

import com.omartobar.backendTienda.Entity.Usuarios;
import com.omartobar.backendTienda.Service.UsuariosService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")

public class UsuariosController {

    private final UsuariosService usuariosService;

    public UsuariosController(UsuariosService usuariosService) {
        this.usuariosService = usuariosService;
    }

    @GetMapping
    public List<Usuarios> getAllUsuarios() {
        return usuariosService.getAllUsuarios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUsuarioById(@PathVariable Integer id) {
        try {
            Usuarios usuarioSolicitado = usuariosService.getUsuarioById(id);
            return new ResponseEntity<>(usuarioSolicitado, HttpStatus.ACCEPTED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Object> createUsuario(@Valid @RequestBody Usuarios usuario) {
        try {
            Usuarios createdUsuario = usuariosService.saveUsuario(usuario);
            return new ResponseEntity<>(createdUsuario, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUsuario(
            @PathVariable Integer id,
            @Valid @RequestBody Usuarios usuario) {

        try {
            usuariosService.updateUsuario(id, usuario);
            return ResponseEntity.ok("Usuario actualizado correctamente");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUsuario(@PathVariable Integer id) {
        try {
            usuariosService.deleteUsuario(id);
            return ResponseEntity.ok("Usuario eliminado correctamente");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        }
    }
}
