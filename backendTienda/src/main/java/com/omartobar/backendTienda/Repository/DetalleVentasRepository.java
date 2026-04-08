package com.omartobar.backendTienda.Repository;

import com.omartobar.backendTienda.Entity.DetalleVentas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleVentasRepository extends JpaRepository<DetalleVentas, Integer> {

}