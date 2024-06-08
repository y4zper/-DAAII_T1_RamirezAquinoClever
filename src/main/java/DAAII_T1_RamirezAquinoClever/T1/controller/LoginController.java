package DAAII_T1_RamirezAquinoClever.T1.controller;

import DAAII_T1_RamirezAquinoClever.T1.model.bd.Usuario;
import DAAII_T1_RamirezAquinoClever.T1.service.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
@AllArgsConstructor
public class LoginController {

    private UsuarioService usuarioService;
    @GetMapping("/login")
    public String login(){
        return "auth/frmlogin";
    }

    @GetMapping("/login-success")
    public String loginSuccess(HttpServletRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String nombreUsuario = auth.getName();
        request.getSession().setAttribute("nomusuario", nombreUsuario);
        return "redirect:/auth/dashboard";
    }
    @GetMapping("/dashboard")
    public String dashboard(){
        return "auth/home";
    }

    @GetMapping("/register")
    public String Registro(HttpServletRequest request){
        return "auth/frmRegistro";
    }

    @PostMapping("/save-user")
    public String guardarUsuario(@ModelAttribute Usuario usuario){
        usuarioService.guardarUsuario(usuario);
        return "auth/frmLogin";
    }

}