package DAAII_T1_RamirezAquinoClever.T1.service;

import DAAII_T1_RamirezAquinoClever.T1.model.bd.Rol;
import DAAII_T1_RamirezAquinoClever.T1.model.bd.Usuario;
import DAAII_T1_RamirezAquinoClever.T1.repository.RolRepository;
import DAAII_T1_RamirezAquinoClever.T1.repository.UsuarioRepository;
import DAAII_T1_RamirezAquinoClever.T1.util.RandomPassword;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
@AllArgsConstructor
public class UsuarioService implements IUsuarioService {

    private UsuarioRepository usuarioRepository;
    private RolRepository rolRepository;
    private RandomPassword randomPassword;

    @Override
    public Usuario buscarUsuarioXNomUsuario(String nomusuario) {
        return usuarioRepository.findByNomusuario(nomusuario);
    }
    @Override
    public Usuario guardarUsuario(Usuario usuario) {
        usuario.setActivo(true);
        Rol usuarioRol = rolRepository.findByNomrol("ADMIN");
        usuario.setRoles(new HashSet<>(Arrays.asList(usuarioRol)));
        return usuarioRepository.save(usuario);
    }

    @Override
    public void actualizarUsuario(Usuario usuario) {
        usuarioRepository.actualizarUsuario(
                usuario.getNombres(),usuario.getApellidos(),
                usuario.getActivo(),usuario.getIdusuario()
        );
    }
    @Override
    public List<Usuario> listarUsuario() {
        return usuarioRepository.findAll();
    }
    @Override
    public Usuario buscarUsuarioXIdUsuario(Integer idusuario) {
        return usuarioRepository.findById(idusuario).orElse(null);
    }

    public void changePassword(String username, String newpassword) {
        Usuario usuario = usuarioRepository.findByNomusuario(username);
        if (usuario != null) {
            if (!es_valido(newpassword)) {
                throw new IllegalArgumentException("La contraseña no es valida");
            }
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String passwordCifrada = passwordEncoder.encode(newpassword);
            usuario.setPassword(passwordCifrada);
            usuarioRepository.save(usuario);
        } else {
            throw new IllegalArgumentException("Usuario no encontrado: " + username);
        }
    }


    private boolean es_valido(String password) {
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        return password.matches(regex);
    }








}
