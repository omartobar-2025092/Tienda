package com.omartobar.backendTienda.Service;

import com.omartobar.backendTienda.Entity.DetalleVentas;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DetalleVentasService {

    List<DetalleVentas> getAllDetalleVentas();
    DetalleVentas getDetalleVentaById(Integer id);
    DetalleVentas saveDetalleVenta(DetalleVentas detalleVenta) throws RuntimeException;
    DetalleVentas updateDetalleVenta(Integer id, DetalleVentas detalleVenta);
    void deleteDetalleVenta(Integer id);

}
