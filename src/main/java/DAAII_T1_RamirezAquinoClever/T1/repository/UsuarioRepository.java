package DAAII_T1_RamirezAquinoClever.T1.repository;

import DAAII_T1_RamirezAquinoClever.T1.model.bd.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    //Select * from usuario where nomusuario = ''
    Usuario findByNomusuario(String nomusuario);


    @Transactional
    @Modifying
    @Query(value = "UPDATE usuario SET nombres=:nombres, apellidos=:apellidos, " +
            "activo=:activo where idusuario=:idusuario",
            nativeQuery = true)
    void actualizarUsuario(@Param("nombres") String nombres,
                           @Param("apellidos") String apellidos,
                           @Param("activo") Boolean activo,
                           @Param("idusuario") Integer idusuario);
}