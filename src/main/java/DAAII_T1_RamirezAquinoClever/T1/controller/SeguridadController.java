package DAAII_T1_RamirezAquinoClever.T1.controller;

import DAAII_T1_RamirezAquinoClever.T1.model.bd.Usuario;
import DAAII_T1_RamirezAquinoClever.T1.model.dto.ResultadoDto;
import DAAII_T1_RamirezAquinoClever.T1.model.dto.UsuarioDto;
import DAAII_T1_RamirezAquinoClever.T1.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/seguridad")
public class SeguridadController {
    private UsuarioService usuarioService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @GetMapping("/usuario")
    public String frmMantUsuario(Model model){
        model.addAttribute("listaUsuarios",
                usuarioService.listarUsuario());
        return "seguridad/formusuario";
    }
    @PostMapping("/usuario")
    @ResponseBody
    public ResultadoDto registrarUsuario(@RequestBody UsuarioDto usuarioDto){
        String mensaje = "Usuario registrado correctamente";
        boolean respuesta = true;
        try {
            Usuario usuario = new Usuario();
            usuario.setNombres(usuarioDto.getNombres());
            usuario.setApellidos(usuarioDto.getApellidos());
            if(usuarioDto.getIdusuario() > 0){
                usuario.setIdusuario(usuarioDto.getIdusuario());
                usuario.setActivo(usuarioDto.getActivo());
                usuarioService.actualizarUsuario(usuario);
            }else{
                usuario.setNomusuario(usuarioDto.getNomusuario());
                usuario.setEmail(usuarioDto.getEmail());
                usuario.setPassword(bCryptPasswordEncoder.encode(usuarioDto.getPassword()));
                usuarioService.guardarUsuario(usuario);
            }
        }catch (Exception ex){
            mensaje = "Usuario no registrado, error en la BD";
            respuesta = false;
        }
        return ResultadoDto.builder().mensaje(mensaje).respuesta(respuesta).build();
    }

    @GetMapping("/usuario/{id}")
    @ResponseBody
    public Usuario frmMantUsuario(@PathVariable("id") int id){
        return usuarioService.buscarUsuarioXIdUsuario(id);
    }
    @GetMapping("/usuario/lista")
    @ResponseBody
    public List<Usuario> listaUsuario(){
        return usuarioService.listarUsuario();
    }

}
