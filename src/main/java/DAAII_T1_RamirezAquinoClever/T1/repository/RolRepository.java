package DAAII_T1_RamirezAquinoClever.T1.repository;

import DAAII_T1_RamirezAquinoClever.T1.model.bd.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {
    Rol findByNomrol(String nomrol);
}