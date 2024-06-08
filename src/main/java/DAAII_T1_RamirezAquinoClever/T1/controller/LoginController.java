package DAAII_T1_RamirezAquinoClever.T1.controller;

import DAAII_T1_RamirezAquinoClever.T1.model.bd.Usuario;
import DAAII_T1_RamirezAquinoClever.T1.service.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        return "auth/frmregister";
    }

    @PostMapping("/save-user")
    public String guardarUsuario(@ModelAttribute Usuario usuario){
        usuarioService.guardarUsuario(usuario);
        return "auth/frmLogin";
    }

    @GetMapping("/change-password")
    public String ChangePassword(@ModelAttribute Usuario usuario ) {
        return "auth/frmpassword";
    }

    @PostMapping("/change-password")
    public String changePassword(@RequestParam("newPassword") String newPassword,
                                  Authentication authentication,
                                  RedirectAttributes redirectAttributes) {
        String username = authentication.getName();
        try {
            usuarioService.changePassword(username, newPassword);
            redirectAttributes.addFlashAttribute("success", "Tu contraseña ha sido actualizada");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/auth/change-password";
    }











}