package com.omartobar.backendTienda.Repository;

import com.omartobar.backendTienda.Entity.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuarios, Integer> {
    Usuarios findByUsername(String username);
}
