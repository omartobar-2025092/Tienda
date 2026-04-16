package com.omartobar.backendTienda.Controller;

import com.omartobar.backendTienda.Entity.Usuarios;
import com.omartobar.backendTienda.Repository.UsuariosRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegisterController {

    private final UsuariosRepository usuariosRepository;

    public RegisterController(UsuariosRepository usuariosRepository) {
        this.usuariosRepository = usuariosRepository;
    }

    @GetMapping("/register")
    public String mostrarRegister() {
        return "register"; // Nombre del HTML que pasaste
    }

    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String confirmPassword,
                           Model model,
                           HttpSession session) {

        // Verificador de contraseñas//
        if (!password.equals(confirmPassword)) {
            model.addAttribute("error", "Las contraseñas no coinciden");
            return "register";
        }

        //Verificador si el usuario ya existe//
        Usuarios existingUser = usuariosRepository.findByUsername(username);
        if (existingUser != null) {
            model.addAttribute("error", "El usuario ya existe");
            return "register";
        }

        Usuarios nuevoUsuario = new Usuarios();
        nuevoUsuario.setUsername(username);
        nuevoUsuario.setPasword(password);

        usuariosRepository.save(nuevoUsuario);

        return "redirect:/login";
    }
}
