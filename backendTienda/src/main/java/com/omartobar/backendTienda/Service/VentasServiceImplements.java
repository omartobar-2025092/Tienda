package com.omartobar.backendTienda.Service;

import com.omartobar.backendTienda.Entity.Ventas;
import com.omartobar.backendTienda.Repository.VentasRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VentasServiceImplements implements VentasService {

    private final VentasRepository ventasRepository;

    public VentasServiceImplements(VentasRepository ventasRepository) {
        this.ventasRepository = ventasRepository;
    }

    @Override
    public List<Ventas> getAllVentas() {
        return ventasRepository.findAll();
    }

    @Override
    public Ventas getVentaById(Integer id) {
        return ventasRepository.findById(id).orElse(null);
    }

    @Override
    public Ventas saveVenta(Ventas venta) throws RuntimeException {
        return ventasRepository.save(venta);
    }

    @Override
    public Ventas updateVenta(Integer id, Ventas venta) {
        Optional<Ventas> ventaExistente = ventasRepository.findById(id);

        if (ventaExistente.isPresent()) {
            Ventas newVenta = ventaExistente.get();
            newVenta.setFechaVenta(venta.getFechaVenta());
            newVenta.setTotal(venta.getTotal());
            newVenta.setEstado(venta.getEstado());
            newVenta.setDpiCliente(venta.getDpiCliente());
            newVenta.setCodigoUsuario(venta.getCodigoUsuario());

            return ventasRepository.save(newVenta);
        } else {
            return null;
        }
    }

    @Override
    public void deleteVenta(Integer id) {
        ventasRepository.deleteById(id);
    }
}
