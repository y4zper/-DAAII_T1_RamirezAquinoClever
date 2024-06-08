package DAAII_T1_RamirezAquinoClever.T1.service;

import DAAII_T1_RamirezAquinoClever.T1.model.bd.Usuario;

import java.util.List;

public interface IUsuarioService {

    Usuario buscarUsuarioXNomUsuario(String nomusuario);
    Usuario guardarUsuario(Usuario usuario);
    void actualizarUsuario(Usuario usuario);
    List<Usuario> listarUsuario();
    Usuario buscarUsuarioXIdUsuario(Integer idusuario);
}
