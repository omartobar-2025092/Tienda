package com.omartobar.backendTienda.Service;

import com.omartobar.backendTienda.Entity.Ventas;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VentasService {

    List<Ventas> getAllVentas();
    Ventas getVentaById(Integer id);
    Ventas saveVenta(Ventas venta) throws RuntimeException;
    Ventas updateVenta(Integer id, Ventas venta);
    void deleteVenta(Integer id);

}
