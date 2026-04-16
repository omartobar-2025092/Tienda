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
public class LoginController {

    private final UsuariosRepository usuariosRepository;

    public LoginController(UsuariosRepository usuariosRepository) {
        this.usuariosRepository = usuariosRepository;
    }

    @GetMapping("/")
    public String inicio() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String mostrarLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String usuario,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {

        //Busca al usuario directamente en la base de datos//
        Usuarios user = usuariosRepository.findByUsername(usuario);

        if (user != null && user.getPasword().equals(password)) {
            session.setAttribute("usuarioLogueado", usuario);
            String rol = (user.getRol() != null) ? user.getRol().toUpperCase() : "USER";
            session.setAttribute("rolUsuario", rol);
            return "redirect:/tienda";
        } else {
            model.addAttribute("error", "Usuario y contraseña incorrecta");
            return "login";
        }
    }

    @GetMapping("/tienda")
    public String mostrarTienda(HttpSession session, Model model) {
        if (session.getAttribute("usuarioLogueado") == null) {
            return "redirect:/login";
        }
        model.addAttribute("rolUsuario", session.getAttribute("rolUsuario"));
        model.addAttribute("usuarioLogueado", session.getAttribute("usuarioLogueado"));
        return "tienda";
    }
}